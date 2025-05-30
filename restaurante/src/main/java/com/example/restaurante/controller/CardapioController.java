package com.example.restaurante.controller;

import com.example.restaurante.dto.ItemCardapioDTO;
import com.example.restaurante.service.CardapioService;
import com.example.restaurante.service.integration.CardapioExternoApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cardapio")
@Tag(name = "Cardapio", description = "Gerenciamento de cardapio")
public class CardapioController {
    private final CardapioService cardapioService;

    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @Operation(summary = "Sincronizar cardápio externo.")
    @GetMapping
    public ResponseEntity<List<ItemCardapioDTO>> sincronizar() {
        return new ResponseEntity<>(cardapioService.sincronizar(), HttpStatus.OK);
    }
}
