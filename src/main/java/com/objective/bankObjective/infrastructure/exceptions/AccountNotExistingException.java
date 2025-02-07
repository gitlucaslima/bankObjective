package com.objective.bankObjective.infrastructure.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AccountExistingException extends BadRequestException {
    public AccountExistingException() {
        super("Não foi possível criar esta conta");
    }
}
