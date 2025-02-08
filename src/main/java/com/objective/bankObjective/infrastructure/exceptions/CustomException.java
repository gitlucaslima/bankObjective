package com.objective.bankObjective.infrastructure.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Data
public class CustomException {

    private HttpStatus status;
    private String message;
    private List<String> errors;

}
