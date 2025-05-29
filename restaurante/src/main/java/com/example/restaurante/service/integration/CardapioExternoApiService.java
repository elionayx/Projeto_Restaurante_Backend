package com.example.restaurante.service.integration;

import com.example.restaurante.dto.ItemCardapioExternoDTO;

import com.example.restaurante.model.ItemCardapio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "cardapioexterno", url = "${external.api.cardapio.base-url}")
public interface CardapioExternoApiService {

    @GetMapping("/cardapio")
    List<ItemCardapioExternoDTO> recuperarCardapio();

    @PostMapping("/cardapio/filtrar")
    List<ItemCardapio> recuperarCardapioFiltrado(List<Long> items);

    @GetMapping("/cardapio/{id}")
    ItemCardapioExternoDTO recuperarCardapioItem(@PathVariable("id") Long id);
}