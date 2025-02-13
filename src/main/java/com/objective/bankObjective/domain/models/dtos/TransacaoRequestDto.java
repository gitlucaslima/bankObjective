package com.objective.bankObjective.domain.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransacaoRequestDto(
        @JsonProperty("forma_pagamento")
        @NotNull(message = "A forma de pagamento é obrigatória")
        String formaPagamento,

        @JsonProperty("numero_conta")
        @NotNull(message = "O número da conta não pode ser nulo")
        @Min(value = 1, message = "O número da conta deve ser maior que zero")
        Long numeroConta,

        @NotNull(message = "O valor da transação é obrigatório")
        @Min(value = 1, message = "O valor da transação deve ser maior que zero")
        BigDecimal valor
) {
}
