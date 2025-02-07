package com.objective.bankObjective.infrastructure.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegativeBalanceException extends BadRequestException {
    public NegativeBalanceException() {
        super("Não é possível criar conta com saldo negativo");
    }
}
