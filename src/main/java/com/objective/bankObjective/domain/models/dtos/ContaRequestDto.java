package com.objective.bankObjective.domain.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ContaRequestDto(
        @JsonProperty("numero_conta")
        @NotNull(message = "O número da conta não pode ser nulo")
        @Min(value = 1, message = "O número da conta deve ser maior que zero")
        Long numeroConta,

        @NotNull(message = "O saldo da conta não pode ser nulo")
        @Min(value = 0, message = "O saldo da conta deve ser maior ou igual a zero")
        BigDecimal saldo
) {
}
