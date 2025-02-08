package com.objective.bankObjective.controllers;

import com.objective.bankObjective.domain.models.dtos.ContaRequestDto;
import com.objective.bankObjective.domain.models.dtos.ContaResponseDto;
import com.objective.bankObjective.domain.services.ContaService;
import com.objective.bankObjective.infrastructure.exceptions.AccountExistingException;
import com.objective.bankObjective.infrastructure.exceptions.AccountNotExistingException;
import com.objective.bankObjective.infrastructure.exceptions.NegativeBalanceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/conta")
@RestController
@RequiredArgsConstructor
@Tag(name = "Conta", description = "API para operações de conta")
public class ContaResource {

    private final ContaService contaService;

    @PostMapping
    @Operation(summary = "Nova conta", description = "Cria uma nova conta")
    public ContaResponseDto salvar(@Valid @RequestBody ContaRequestDto contaRequestDto) throws AccountExistingException, NegativeBalanceException {
        return contaService.criarConta(contaRequestDto);
    }

    @GetMapping()
    @Operation(summary = "Buscar conta", description = "Busca uma conta pelo número")
    public ResponseEntity<ContaResponseDto> find(@Valid @RequestParam(name = "numero_conta") Long numeroConta) throws AccountNotExistingException {
        return ResponseEntity.ok(contaService.find(numeroConta));
    }

}
