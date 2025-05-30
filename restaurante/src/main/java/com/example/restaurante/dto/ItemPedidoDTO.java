package com.example.restaurante.dto;

import lombok.Data;

@Data
public class ItemPedidoDTO {
    private Long id;
    private Long itemCardapioId;
    private String nomeItem;
    private Integer quantidade;
    private Double precoUnitario;
    private Double subtotal;
}