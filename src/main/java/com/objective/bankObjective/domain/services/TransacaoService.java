package com.objective.bankObjective.domain.services;

import com.objective.bankObjective.domain.models.Conta;
import com.objective.bankObjective.domain.models.FormaPagamento;
import com.objective.bankObjective.domain.models.dtos.TransacaoRequestDto;
import com.objective.bankObjective.domain.models.dtos.TransacaoResponseDto;
import com.objective.bankObjective.infrastructure.repository.ContaRepository;
import com.objective.bankObjective.domain.strategy.FormaPagamentoStrategy;
import com.objective.bankObjective.infrastructure.exceptions.AccountNotExistingException;
import com.objective.bankObjective.infrastructure.exceptions.InsufficientBalanceException;
import com.objective.bankObjective.infrastructure.exceptions.NotFoundPaymentTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final ContaRepository contaRepository;
    private final Map<FormaPagamento, FormaPagamentoStrategy> processaPagamentoPorTipo;

    @Transactional
    public TransacaoResponseDto novaTransacao(TransacaoRequestDto transacaoRequest) throws NotFoundPaymentTypeException, InsufficientBalanceException, AccountNotExistingException {

        var contaEncontrada = contaRepository.findByNumero(transacaoRequest.numeroConta())
                .orElseThrow(AccountNotExistingException::new);

        FormaPagamento formaPagamento = FormaPagamento.fromCodigo(transacaoRequest.formaPagamento());

        FormaPagamentoStrategy estrategiaDePagamento = processaPagamentoPorTipo.get(formaPagamento);

        if (estrategiaDePagamento == null) {
            throw new NotFoundPaymentTypeException();
        }

        if (transacaoRequest.valor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser maior que zero");
        }

        var valorFinal = estrategiaDePagamento.processarPagamento(transacaoRequest.valor());

        if (contaEncontrada.getSaldo().compareTo(valorFinal) < 0) {
            throw new InsufficientBalanceException();
        }

        contaEncontrada.setSaldo((contaEncontrada.getSaldo().subtract(valorFinal).setScale(2, RoundingMode.HALF_EVEN)));

        Conta contaAtualizada = contaRepository.save(contaEncontrada);

        return new TransacaoResponseDto(contaAtualizada.getNumero(), contaAtualizada.getSaldo());
    }

}
