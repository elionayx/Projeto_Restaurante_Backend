package com.example.restaurante.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    @Enumerated(EnumType.STRING)
    private StatusMesa status;

    @OneToMany(mappedBy = "mesa")
    private List<Pedido> pedidos;

    // Getters e Setters
}

