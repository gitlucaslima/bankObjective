package com.objective.bankObjective.domain.models;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("conta")
public class Conta {

    private String id;
    private Long numero;
    private BigDecimal saldo;

}
