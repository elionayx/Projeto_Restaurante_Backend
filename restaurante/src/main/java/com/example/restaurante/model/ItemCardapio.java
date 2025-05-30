package com.example.restaurante.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ItemCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Double preco;

    @Enumerated(EnumType.STRING)
    private CategoriaItem categoria;

}
