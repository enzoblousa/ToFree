package com.inflatable.freelancer.controller;

import com.inflatable.freelancer.dto.InflatableDTO;
import com.inflatable.freelancer.service.InflatableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/inflatables")
@Tag(name = "Brinquedos Infláveis", description = "Operações CRUD para brinquedos infláveis")
public class InflatableController {

    @Autowired
    private InflatableService inflatableService;

    @GetMapping
    @Operation(summary = "Listar brinquedos infláveis", description = "Retorna todos os brinquedos infláveis cadastrados")
    public ResponseEntity<List<InflatableDTO>> getAllInflatables() {
        List<InflatableDTO> inflatables = inflatableService.findAll();
        return ResponseEntity.ok(inflatables);
    }

    @GetMapping("/available")
    @Operation(summary = "Listar brinquedos disponíveis", description = "Retorna apenas os brinquedos infláveis disponíveis")
    public ResponseEntity<List<InflatableDTO>> getAvailableInflatables() {
        List<InflatableDTO> availableInflatables = inflatableService.findAvailable();
        return ResponseEntity.ok(availableInflatables);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar brinquedo por ID", description = "Retorna um brinquedo específico pelo ID")
    @ApiResponse(responseCode = "200", description = "Brinquedo encontrado")
    @ApiResponse(responseCode = "404", description = "Brinquedo não encontrado")
    public ResponseEntity<InflatableDTO> getInflatableById(@PathVariable Long id) {
        InflatableDTO inflatable = inflatableService.findById(id);
        return ResponseEntity.ok(inflatable);
    }

    @PostMapping
    @Operation(summary = "Criar brinquedo", description = "Adiciona um novo brinquedo inflável ao sistema")
    public ResponseEntity<InflatableDTO> createInflatable(@Valid @RequestBody InflatableDTO inflatableDTO) {
        InflatableDTO createdInflatable = inflatableService.create(inflatableDTO);
        return ResponseEntity.ok(createdInflatable);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar brinquedo", description = "Atualiza os dados de um brinquedo existente")
    public ResponseEntity<InflatableDTO> updateInflatable(@PathVariable Long id, @Valid @RequestBody InflatableDTO inflatableDTO) {
        InflatableDTO updatedInflatable = inflatableService.update(id, inflatableDTO);
        return ResponseEntity.ok(updatedInflatable);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar brinquedo", description = "Remove um brinquedo inflável do sistema")
    public ResponseEntity<Void> deleteInflatable(@PathVariable Long id) {
        inflatableService.delete(id);
        return ResponseEntity.noContent().build();
    }
}