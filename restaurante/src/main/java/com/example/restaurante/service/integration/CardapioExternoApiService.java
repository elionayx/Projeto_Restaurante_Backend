package com.example.restaurante.service.integration;

import com.example.restaurante.dto.ItemCardapioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "cardapioexterno", url = "${external.api.cardapio.base-url}")
public interface CardapioExternoApiService {

    @GetMapping("/cardapio")
    List<ItemCardapioDTO> sincronizar();
}