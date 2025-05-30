package com.example.restaurante.mapper;

import com.example.restaurante.dto.ItemCardapioDTO;
import com.example.restaurante.model.ItemCardapio;

public class ItemCardapioMapper {

    public static ItemCardapioDTO toDTO(ItemCardapio entity) {
        if (entity == null) return null;

        ItemCardapioDTO dto = new ItemCardapioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        dto.setPreco(entity.getPreco());
        dto.setCategoria(entity.getCategoria());
        return dto;
    }

    public static ItemCardapio toEntity(ItemCardapioDTO dto) {
        if (dto == null) return null;

        ItemCardapio entity = new ItemCardapio();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setCategoria(dto.getCategoria());
        return entity;
    }
}

