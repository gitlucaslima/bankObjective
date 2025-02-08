package com.objective.bankObjective.domain.models;

import lombok.Getter;

/**
 * Enum que representa as formas de pagamento.
 * P - Pix
 * C - Crédito
 * D - Débito
 */
@Getter
public enum FormaPagamento {
    P("P"),
    C("C"),
    D("D");

    private final String codigo;

    FormaPagamento(String codigo) {
        this.codigo = codigo;
    }

    public static FormaPagamento fromCodigo(String codigo) {
        for (FormaPagamento forma : FormaPagamento.values()) {
            if (forma.getCodigo().equalsIgnoreCase(codigo)) {
                return forma;
            }
        }
        throw new IllegalArgumentException("Forma de pagamento inválida: " + codigo);
    }
}
