package com.example.restaurante.controller;

import com.example.restaurante.dto.MesaDTO;
import com.example.restaurante.mapper.MesaMapper;
import com.example.restaurante.model.Mesa;
import com.example.restaurante.model.StatusMesa;
import com.example.restaurante.service.MesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mesas")
@Tag(name = "Mesas", description = "Gerenciamento das mesas do restaurante")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @Operation(
            summary = "Cadastrar uma nova mesa",
            description = "Cria uma mesa no sistema com status inicial (ex: LIVRE, OCUPADA).",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Mesa criada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MesaDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<MesaDTO> cadastrar(@RequestBody Mesa mesa) {
        Mesa novaMesa = mesaService.cadastrar(mesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(MesaMapper.toDTO(novaMesa));
    }

    @Operation(
            summary = "Listar todas as mesas",
            description = "Retorna uma lista com todas as mesas cadastradas no restaurante."
    )
    @GetMapping
    public List<MesaDTO> listar() {
        return mesaService.listarTodas().stream()
                .map(MesaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "Buscar uma mesa por ID",
            description = "Retorna os dados de uma mesa específica pelo seu ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Mesa encontrada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MesaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Mesa não encontrada", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public MesaDTO buscarPorId(
            @Parameter(description = "ID da mesa") @PathVariable Long id) {
        return MesaMapper.toDTO(mesaService.buscarPorId(id));
    }

    @Operation(
            summary = "Atualizar o status de uma mesa",
            description = "Permite atualizar o status da mesa (LIVRE, OCUPADA, RESERVADA).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MesaDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Mesa não encontrada", content = @Content)
            }
    )
    @PutMapping("/{id}/status")
    public MesaDTO atualizarStatus(
            @Parameter(description = "ID da mesa") @PathVariable Long id,
            @Parameter(description = "Novo status da mesa") @RequestParam StatusMesa status) {
        return MesaMapper.toDTO(mesaService.atualizarStatus(id, status));
    }

    @Operation(
            summary = "Remover uma mesa",
            description = "Exclui uma mesa do sistema pelo seu ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Mesa removida com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Mesa não encontrada", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
            @Parameter(description = "ID da mesa") @PathVariable Long id) {
        mesaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
