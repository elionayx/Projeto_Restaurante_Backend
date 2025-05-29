package com.example.restaurante.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    private List<ItemCardapio> itens;

    @Enumerated(EnumType.STRING)
    private PedidoStatus status;

    public Pedido() {
    }
}

