package com.example.restaurante.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

}

