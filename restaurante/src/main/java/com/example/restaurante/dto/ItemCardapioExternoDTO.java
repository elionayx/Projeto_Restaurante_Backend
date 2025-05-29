package com.example.restaurante.dto;

import java.math.BigDecimal;

public class ItemCardapioExternoDTO {

    private Long idExterno;
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private String categoria;

    public ItemCardapioExternoDTO() {
    }

    public ItemCardapioExternoDTO(Long idExterno, String nome, BigDecimal preco, String descricao, String categoria) {
        this.idExterno = idExterno;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.categoria = categoria;
    }


    public Long getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(Long idExterno) {
        this.idExterno = idExterno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ItemCardapioExternoDTO{" +
                "idExterno=" + idExterno +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}