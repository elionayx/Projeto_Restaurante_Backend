package com.example.restaurante.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "item_cardapio_id")
    private ItemCardapio itemCardapio;

}
