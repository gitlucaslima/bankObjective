package com.objective.bankObjective.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotExistingException extends Exception  {
    public AccountNotExistingException() {
        super("Não foi possível encontrar esta conta");
    }
}
