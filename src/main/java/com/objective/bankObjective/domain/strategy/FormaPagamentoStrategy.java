package com.objective.bankObjective.domain.strategy;

import com.objective.bankObjective.domain.models.FormaPagamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface FormaPagamentoStrategy {

    BigDecimal processarPagamento(BigDecimal valor);

    FormaPagamento getFormaPagamentoType();

}