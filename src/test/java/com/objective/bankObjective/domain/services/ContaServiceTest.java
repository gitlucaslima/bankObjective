package com.objective.bankObjective.domain.services;

import com.objective.bankObjective.domain.models.Conta;
import com.objective.bankObjective.domain.models.dtos.ContaRequestDto;
import com.objective.bankObjective.domain.models.dtos.ContaResponseDto;
import com.objective.bankObjective.infrastructure.exceptions.AccountExistingException;
import com.objective.bankObjective.infrastructure.exceptions.AccountNotExistingException;
import com.objective.bankObjective.infrastructure.exceptions.NegativeBalanceException;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;

    @Test
    @DisplayName("Deve criar uma conta valida")
    void criarContaValida() throws NegativeBalanceException, AccountExistingException {

        //mock da request
        ContaRequestDto contaRequestDtoMock = new ContaRequestDto(1L, new BigDecimal("100"));

        //mock de retorno do banco
        Conta contaSalva = Conta.builder()
                .numero(contaRequestDtoMock.numeroConta())
                .saldo(contaRequestDtoMock.saldo())
                .build();

        //mock da response
        ContaResponseDto contaResponseDtoMock = new ContaResponseDto(contaSalva.getNumero(), contaSalva.getSaldo());

        //mock do comportamento do banco
        Mockito.when(contaRepository.save(Mockito.any(Conta.class))).thenReturn(contaSalva);

        ContaResponseDto contaResponseDto = contaService.criarConta(contaRequestDtoMock);

        Assertions.assertEquals(contaResponseDtoMock, contaResponseDto);
        Assertions.assertEquals(contaResponseDtoMock.numeroConta(), contaResponseDto.numeroConta());
        Assertions.assertEquals(contaResponseDtoMock.saldo(), contaResponseDto.saldo());
    }

    @Test
    @DisplayName("Deve lançar exceção de saldo negativo")
    void criarContaSaldoNegativo() {
        ContaRequestDto contaRequestDtoMock = new ContaRequestDto(1L, new BigDecimal("-100"));
        Assertions.assertThrows(NegativeBalanceException.class, () -> contaService.criarConta(contaRequestDtoMock));
    }

    @Test
    @DisplayName("Deve lançar uma exceção de conta existente")
    void criarContaExistente() {
        ContaRequestDto contaRequestDtoMock = new ContaRequestDto(1L, new BigDecimal("100"));

        Conta contaExistente = Conta.builder()
                .id("objectid_a3tf5bv4f53e7ghl3")
                .numero(1L)
                .saldo(BigDecimal.valueOf(77))
                .build();

        Mockito.when(contaRepository.findByNumero(1L)).thenReturn(Optional.ofNullable(contaExistente));

        Assertions.assertThrows(AccountExistingException.class, () -> contaService.criarConta(contaRequestDtoMock));

    }

    @Test
    @DisplayName("Deve buscar uma conta com sucesso")
    void buscarContaComSucesso() throws AccountNotExistingException {
        Long numeroConta = 1L;

        Conta contaExistenteMock = Conta.builder()
                .id("objectid_a3tf5bv4f53e7ghl3")
                .numero(1L)
                .saldo(BigDecimal.valueOf(77))
                .build();

        Mockito.when(contaRepository.findByNumero(numeroConta)).thenReturn(Optional.ofNullable(contaExistenteMock));

        ContaResponseDto contaResponseDto = contaService.find(numeroConta);

        assert contaExistenteMock != null;
        Assertions.assertEquals(contaExistenteMock.getNumero(), contaResponseDto.numeroConta());
        Assertions.assertEquals(contaExistenteMock.getSaldo(), contaResponseDto.saldo());

    }

    @Test
    @DisplayName("Deve lançar exceção de conta inexistente")
    void buscarContaNaoExistente()  {
        Assertions.assertThrows(AccountNotExistingException.class, () -> contaService.find(1L));
    }
}