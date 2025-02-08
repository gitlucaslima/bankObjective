package com.objective.bankObjective.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * O status mais adequado para este tipo de exceção é o 422, que indica que a requisição foi bem formada,
 * mas não pôde ser processada devido a erros de regras de negócio.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException() {
        super("Saldo insuficiente para completar esta transação");
    }
}
