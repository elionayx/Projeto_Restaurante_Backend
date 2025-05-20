package com.example.restaurante.model;

import jakarta.persistence.*;

@Entity
public class ItemCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column (nullable = false)
    private Double preco;

    public ItemCardapio() {
    }

    public ItemCardapio(String nome, Double preco, Long id) {
        this.nome = nome;
        this.preco = preco;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
