package com.objective.bankObjective.domain.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ContaResponseDto(
        @JsonProperty("numero_conta") Long numeroConta,
        BigDecimal saldo) {
}
