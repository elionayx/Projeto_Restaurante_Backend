package com.example.restaurante.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class ItemCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long idExterno;

    private String nome;

    @Column (nullable = false)
    private BigDecimal preco;

    private String descricao;

    private String categoria;

    @ManyToMany
    private List<Pedido> pedido;
}