package com.example.restaurante.dto;

import com.example.restaurante.model.StatusPedido;
import lombok.Data;

@Data
public class AtualizarStatusPedidoDTO {
    private StatusPedido status;
}