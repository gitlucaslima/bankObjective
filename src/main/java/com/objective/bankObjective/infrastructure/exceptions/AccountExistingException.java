package com.objective.bankObjective.infrastructure;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountExistingException extends BadRequestException {
    public AccountExistingException(Long accountNumber) {
        super("Conta com número " + accountNumber + " já existente. ");
    }
}
