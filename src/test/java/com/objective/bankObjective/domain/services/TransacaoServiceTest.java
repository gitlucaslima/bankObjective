package com.objective.bankObjective.domain.services;

import com.objective.bankObjective.domain.models.Conta;
import com.objective.bankObjective.domain.models.FormaPagamento;
import com.objective.bankObjective.domain.models.dtos.ContaRequestDto;
import com.objective.bankObjective.domain.models.dtos.TransacaoRequestDto;
import com.objective.bankObjective.domain.models.dtos.TransacaoResponseDto;
import com.objective.bankObjective.domain.strategy.FormaPagamentoStrategy;
import com.objective.bankObjective.infrastructure.exceptions.AccountNotExistingException;
import com.objective.bankObjective.infrastructure.exceptions.InsufficientBalanceException;
import com.objective.bankObjective.infrastructure.exceptions.NegativeBalanceException;
import com.objective.bankObjective.infrastructure.exceptions.NotFoundPaymentTypeException;
import com.objective.bankObjective.infrastructure.repository.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private Map<FormaPagamento, FormaPagamentoStrategy> processaPagamentoPorTipo;  // Mock do Map

    @Mock
    private FormaPagamentoStrategy formaPagamentoStrategyMock;  // Mock da estratégia de pagamento

    @Test
    @DisplayName("Criar uma nova transacao com sucesso")
    void novaTransacao() throws NotFoundPaymentTypeException, AccountNotExistingException, InsufficientBalanceException {
        // Dado
        TransacaoRequestDto transacaoRequestDto = new TransacaoRequestDto("p", 1L, new BigDecimal(10));

        Conta contaMock = Conta.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .saldo(BigDecimal.valueOf(100))
                .numero(1L)
                .build();

        // Configuração do mock do contaRepository
        Mockito.when(contaRepository.findByNumero(transacaoRequestDto.numeroConta())).thenReturn(Optional.of(contaMock));
        Mockito.when(contaRepository.save(Mockito.any(Conta.class))).thenReturn(contaMock);

        // Configuração do mock do Map para retornar a forma de pagamento correta
        Mockito.when(processaPagamentoPorTipo.get(FormaPagamento.fromCodigo("p"))).thenReturn(formaPagamentoStrategyMock);

        // Configuração do mock da estratégia de pagamento
        Mockito.when(formaPagamentoStrategyMock.processarPagamento(Mockito.any(BigDecimal.class))).thenReturn(new BigDecimal(10));

        // Quando
        TransacaoResponseDto transacaoResponseDto = transacaoService.novaTransacao(transacaoRequestDto);

        // Então
        Assertions.assertNotNull(transacaoResponseDto);
        Assertions.assertEquals(1L, transacaoResponseDto.numeroConta());
        Assertions.assertEquals(new BigDecimal("90.00"), transacaoResponseDto.valor());  // Espera-se que o saldo da conta seja 100 - 10
    }

    @Test
    @DisplayName("Deve lançar exceção de transacao com valor negativo")
    void criarContaSaldoNegativo() {
        TransacaoRequestDto transacaoRequestDto = new TransacaoRequestDto("p", 1L, new BigDecimal(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> transacaoService.novaTransacao(transacaoRequestDto));
    }
}