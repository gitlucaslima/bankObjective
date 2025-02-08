package com.objective.bankObjective.infrastructure.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotFoundPaymentTypeException extends BadRequestException {
    public NotFoundPaymentTypeException() {
        super("Tipo de pagamento não encontrada");
    }
}
