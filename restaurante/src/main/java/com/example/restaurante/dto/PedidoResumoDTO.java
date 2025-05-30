package com.example.restaurante.dto;

import com.example.restaurante.model.StatusPedido;
import lombok.Data;

@Data
public class PedidoResumoDTO {
    private Long id;
    private StatusPedido status;
    private Double valorTotal;

}

