package com.example.restaurante.dto;

import com.example.restaurante.model.StatusMesa;
import lombok.Data;

import java.util.List;

@Data
public class MesaDTO {
    private Long id;
    private String numero;
    private StatusMesa status;
    private List<PedidoResumoDTO> pedidos;
}

