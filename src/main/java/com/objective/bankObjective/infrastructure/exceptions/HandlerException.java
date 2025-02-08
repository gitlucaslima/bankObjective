package com.objective.bankObjective.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AccountExistingException.class)
    public ResponseEntity<CustomException> handleInvalidArgument(AccountExistingException exception) {

        CustomException customException = CustomException.builder()
                .status(HttpStatus.CONFLICT)
                .message(exception.getMessage())
                .errors(null)
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(customException);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(NegativeBalanceException.class)
    public ResponseEntity<CustomException> handleInvalidArgument(NegativeBalanceException exception) {

        CustomException customException = CustomException.builder()
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message(exception.getMessage())
                .errors(null)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(customException);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomException> handleInvalidArgument(HttpMessageNotReadableException exception) {

        CustomException customException = CustomException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(exception.getMessage())
                .errors(null)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customException);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccountNotExistingException.class)
    public ResponseEntity<CustomException> handleInvalidArgument(AccountNotExistingException exception) {

        CustomException customException = CustomException.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .errors(null)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customException);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<CustomException> handleInvalidArgument(InsufficientBalanceException exception) {

        CustomException customException = CustomException.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .errors(null)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customException);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(NotFoundPaymentTypeException.class)
    public ResponseEntity<CustomException> handleInvalidArgument(NotFoundPaymentTypeException exception) {

        CustomException customException = CustomException.builder()
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message(exception.getMessage())
                .errors(null)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(customException);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomException> handleInvalidArgument(IllegalArgumentException exception) {

        CustomException customException = CustomException.builder()
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message(exception.getMessage())
                .errors(null)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(customException);
    }
}
