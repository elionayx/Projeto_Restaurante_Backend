package com.example.restaurante.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema (description = "DTO para realizar um pedido.")
public class PedidoDTO {

    @Schema (description = "Id do Cliente", example = "5")
    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    @Schema (description = "Ids dos itens do pedido", example = "1, 2, 3...")
    @NotEmpty(message = "A lista de itens não pode ser vazia")
    private List<@NotNull(message = "ID do item não pode ser nulo") Long> itens;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<Long> getItens() {
        return itens;
    }

    public void setItens(List<Long> itens) {
        this.itens = itens;
    }

}
