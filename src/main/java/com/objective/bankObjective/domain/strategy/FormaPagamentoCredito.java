package com.objective.bankObjective.domain.strategy;

import com.objective.bankObjective.domain.models.FormaPagamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FormaPagamentoCredito implements FormaPagamentoStrategy {

    private static final Double taxa = 1.05;

    @Override
    public BigDecimal processarPagamento(BigDecimal valor) {
        return valor.multiply(new BigDecimal(taxa));
    }

    @Override
    public FormaPagamento getFormaPagamentoType() {
        return FormaPagamento.C;
    }

}
