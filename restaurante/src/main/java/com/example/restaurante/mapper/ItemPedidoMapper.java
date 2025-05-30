package com.example.restaurante.mapper;

import com.example.restaurante.dto.ItemPedidoDTO;
import com.example.restaurante.dto.ItemPedidoRequestDTO;
import com.example.restaurante.model.ItemCardapio;
import com.example.restaurante.model.ItemPedido;
import com.example.restaurante.model.Pedido;

public class ItemPedidoMapper {

    public static ItemPedidoDTO toDTO(ItemPedido entity) {
        if (entity == null) return null;

        ItemPedidoDTO dto = new ItemPedidoDTO();
        dto.setId(entity.getId());
        dto.setItemCardapioId(entity.getItemCardapio().getId());
        dto.setNomeItem(entity.getItemCardapio().getNome());
        dto.setQuantidade(entity.getQuantidade());
        dto.setPrecoUnitario(entity.getItemCardapio().getPreco());
        dto.setSubtotal(entity.getQuantidade() * entity.getItemCardapio().getPreco());

        return dto;
    }

    public static ItemPedido toEntity(ItemPedidoRequestDTO dto, Pedido pedido, ItemCardapio itemCardapio) {
        ItemPedido entity = new ItemPedido();
        entity.setPedido(pedido);
        entity.setItemCardapio(itemCardapio);
        entity.setQuantidade(dto.getQuantidade());
        return entity;
    }
}
