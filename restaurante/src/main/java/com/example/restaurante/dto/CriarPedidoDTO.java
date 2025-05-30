package com.example.restaurante.dto;


import lombok.Data;

import java.util.List;

@Data
public class CriarPedidoDTO {
    private Long mesaId;
    private List<ItemPedidoRequestDTO> itens;
}
