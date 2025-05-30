package com.example.restaurante.dto;

import com.example.restaurante.model.CategoriaItem;
import lombok.Data;

@Data
public class ItemCardapioDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private CategoriaItem categoria;

}
