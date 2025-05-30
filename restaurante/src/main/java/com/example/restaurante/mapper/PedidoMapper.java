package com.example.restaurante.mapper;

import com.example.restaurante.dto.PedidoDTO;
import com.example.restaurante.model.Pedido;

import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoDTO toDTO(Pedido entity) {
        if (entity == null) return null;

        PedidoDTO dto = new PedidoDTO();
        dto.setId(entity.getId());
        dto.setDataHora(entity.getDataHora());
        dto.setStatus(entity.getStatus());
        dto.setValorTotal(entity.getValorTotal());
        dto.setFormaPagamento(entity.getFormaPagamento());
        dto.setMesaId(entity.getMesa().getId());

        if (entity.getItens() != null) {
            dto.setItens(
                    entity.getItens().stream()
                            .map(ItemPedidoMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}

