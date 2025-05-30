package com.example.restaurante.mapper;


import com.example.restaurante.dto.MesaDTO;
import com.example.restaurante.dto.PedidoResumoDTO;
import com.example.restaurante.model.Mesa;
import com.example.restaurante.model.Pedido;

import java.util.stream.Collectors;

public class MesaMapper {

    public static MesaDTO toDTO(Mesa mesa) {
        if (mesa == null) return null;

        MesaDTO dto = new MesaDTO();
        dto.setId(mesa.getId());
        dto.setNumero(mesa.getNumero());
        dto.setStatus(mesa.getStatus());

        if (mesa.getPedidos() != null) {
            dto.setPedidos(
                    mesa.getPedidos().stream()
                            .map(MesaMapper::toPedidoResumoDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    private static PedidoResumoDTO toPedidoResumoDTO(Pedido pedido) {
        PedidoResumoDTO dto = new PedidoResumoDTO();
        dto.setId(pedido.getId());
        dto.setStatus(pedido.getStatus());
        dto.setValorTotal(pedido.getValorTotal());
        return dto;
    }
}
