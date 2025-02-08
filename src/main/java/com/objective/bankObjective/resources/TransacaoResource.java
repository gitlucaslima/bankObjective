package com.objective.bankObjective.controllers;

import com.objective.bankObjective.domain.models.dtos.TransacaoRequestDto;
import com.objective.bankObjective.domain.models.dtos.TransacaoResponseDto;
import com.objective.bankObjective.domain.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/transacao")
@RestController
@RequiredArgsConstructor
@Tag(name = "Transação", description = "API para operações de transação")
public class TransacaoResource {

    private final TransacaoService transacaoService;

    @PostMapping()
    @Operation(summary = "Nova transação", description = "Realiza uma nova transação")
    public TransacaoResponseDto novaTransacao(@Valid @RequestBody TransacaoRequestDto transacaoRequestDto) throws Exception {
        return transacaoService.novaTransacao(transacaoRequestDto);
    }

}
