package com.example.restaurante.dto;

import com.example.restaurante.model.FormaPagamento;
import lombok.Data;

@Data
public class FinalizarPedidoDTO {
    private FormaPagamento formaPagamento;
}
