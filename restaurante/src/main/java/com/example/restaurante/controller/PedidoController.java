package com.example.restaurante.controller;

import com.example.restaurante.dto.CriarPedidoDTO;
import com.example.restaurante.dto.PedidoDTO;
import com.example.restaurante.mapper.PedidoMapper;
import com.example.restaurante.model.FormaPagamento;
import com.example.restaurante.model.Pedido;
import com.example.restaurante.model.StatusPedido;
import com.example.restaurante.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Gerenciamento de pedidos do restaurante")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(
            summary = "Criar um novo pedido",
            description = "Cria um pedido vinculado a uma mesa com itens do cardápio.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody CriarPedidoDTO dto) {
        Pedido pedido = pedidoService.criarPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(PedidoMapper.toDTO(pedido));
    }

    @Operation(
            summary = "Listar todos os pedidos",
            description = "Retorna uma lista de todos os pedidos cadastrados."
    )
    @GetMapping
    public List<PedidoDTO> listar() {
        return pedidoService.listarTodos().stream()
                .map(PedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "Buscar um pedido por ID",
            description = "Retorna os detalhes de um pedido específico pelo seu ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public PedidoDTO buscarPorId(
            @Parameter(description = "ID do pedido") @PathVariable Long id) {
        return PedidoMapper.toDTO(pedidoService.buscarPorId(id));
    }

    @Operation(
            summary = "Atualizar o status de um pedido",
            description = "Permite atualizar o status de um pedido (ABERTO, EM_PREPARO, FINALIZADO, CANCELADO).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content)
            }
    )
    @PutMapping("/{id}/status")
    public PedidoDTO atualizarStatus(
            @Parameter(description = "ID do pedido") @PathVariable Long id,
            @Parameter(description = "Novo status do pedido") @RequestParam StatusPedido status) {
        return PedidoMapper.toDTO(pedidoService.atualizarStatus(id, status));
    }


}
