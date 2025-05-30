package com.example.restaurante.dto;

import com.example.restaurante.model.FormaPagamento;
import com.example.restaurante.model.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDTO {
    private Long id;
    private LocalDateTime dataHora;
    private StatusPedido status;
    private Double valorTotal;
    private FormaPagamento formaPagamento;
    private Long mesaId;
    private List<ItemPedidoDTO> itens;

    // Getters e Setters
}
