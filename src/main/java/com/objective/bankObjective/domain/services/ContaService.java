package com.objective.bankObjective.domain.services;

import com.objective.bankObjective.domain.models.Conta;
import com.objective.bankObjective.domain.models.dtos.ContaRequestDto;
import com.objective.bankObjective.domain.models.dtos.ContaResponseDto;
import com.objective.bankObjective.infrastructure.repository.ContaRepository;
import com.objective.bankObjective.infrastructure.exceptions.AccountExistingException;
import com.objective.bankObjective.infrastructure.exceptions.AccountNotExistingException;
import com.objective.bankObjective.infrastructure.exceptions.NegativeBalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;

    @Transactional
    public ContaResponseDto criarConta(ContaRequestDto contaRequestDto) throws AccountExistingException, NegativeBalanceException {

        if (contaRepository.findByNumero(contaRequestDto.numeroConta()).isPresent())
            throw new AccountExistingException();

        if (contaRequestDto.saldo().compareTo(BigDecimal.ZERO) < 0)
            throw new NegativeBalanceException();

        Conta conta = Conta.builder()
                .saldo(contaRequestDto.saldo())
                .numero(contaRequestDto.numeroConta())
                .build();

        Conta contaSalva = contaRepository.save(conta);

        return new ContaResponseDto(contaSalva.getNumero(), contaSalva.getSaldo());
    }

    public ContaResponseDto find(Long numeroConta) throws AccountNotExistingException {
        var conta = contaRepository.findByNumero(numeroConta).orElseThrow(AccountNotExistingException::new);
        return new ContaResponseDto(conta.getNumero(), conta.getSaldo());
    }


}
