package com.objective.bankObjective.domain.strategy;

import com.objective.bankObjective.domain.models.FormaPagamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FormaPagamentoPix implements FormaPagamentoStrategy {

    @Override
    public BigDecimal processarPagamento(BigDecimal valor) {
        return valor;
    }

    @Override
    public FormaPagamento getFormaPagamentoType() {
        return FormaPagamento.P;
    }

}
