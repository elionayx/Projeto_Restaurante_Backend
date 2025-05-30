package com.example.restaurante.controller;

import com.example.restaurante.dto.PedidoDTO;
import com.example.restaurante.mapper.PedidoMapper;
import com.example.restaurante.model.FormaPagamento;
import com.example.restaurante.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamento")
@Tag(name = "Pagamentos", description = "Pagamentos")
public class PagamentoController {
    private final PedidoService pedidoService;

    public PagamentoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(
            summary = "Finalizar um pedido",
            description = "Finaliza o pedido e registra a forma de pagamento (DINHEIRO, CARTAO, PIX).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido finalizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Pedido não pode ser finalizado nesse status", content = @Content)
            }
    )

    @PutMapping("/{id}/finalizar")
    public PedidoDTO finalizarPedido(
            @Parameter(description = "ID do pedido") @PathVariable Long id,
            @Parameter(description = "Forma de pagamento") @RequestParam FormaPagamento pagamento) {
        return PedidoMapper.toDTO(pedidoService.finalizarPedido(id, pagamento));
    }
}
