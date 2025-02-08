package com.objective.bankObjective.resources;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@Tag(name = "")
public class HealthCheck {

    @GetMapping
    public ResponseEntity<String> check() {
        return new ResponseEntity<>("", org.springframework.http.HttpStatus.OK);
    }
}
