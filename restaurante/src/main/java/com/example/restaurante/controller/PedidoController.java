package com.example.restaurante.controller;

import com.example.restaurante.dto.PedidoDTO;
import com.example.restaurante.model.Pedido;
import com.example.restaurante.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Gerenciamento de pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Realizar um novo pedido")
    @PostMapping
    public ResponseEntity<String> realizarPedido(@Valid @RequestBody PedidoDTO pedidoDTO) {
        pedidoService.realizarPedido(pedidoDTO);
        return new ResponseEntity<>("Pedido realizado com sucesso", HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar pedido por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPedido(id);
        return ResponseEntity.ok(pedido);
    }

    @Operation(summary = "Atualizar status do pedido")
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        pedidoService.atualizarStatus(id, status);
        return ResponseEntity.ok("Status do pedido atualizado para " + status);
    }
}
