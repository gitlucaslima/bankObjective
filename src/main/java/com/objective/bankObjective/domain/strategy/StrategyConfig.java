package com.objective.bankObjective.domain.strategy;

import com.objective.bankObjective.domain.models.FormaPagamento;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class StrategyConfig {

    private final List<FormaPagamentoStrategy> formaPagamentoStrategies;

    @Bean
    public Map<FormaPagamento, FormaPagamentoStrategy> processaPagamentoPorTipo() {
        Map<FormaPagamento, FormaPagamentoStrategy> pagamentoPorTipo = new EnumMap<>(FormaPagamento.class);
        formaPagamentoStrategies.forEach(formaPagamentoStrategy -> pagamentoPorTipo.put(formaPagamentoStrategy.getFormaPagamentoType(), formaPagamentoStrategy));
        return pagamentoPorTipo;
    }

}
