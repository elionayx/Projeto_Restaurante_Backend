package com.example.restaurante.controller;

import com.example.restaurante.dto.ItemCardapioExternoDTO;
import com.example.restaurante.service.integration.CardapioExternoApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cardapio")
@Tag(name = "Cardapio", description = "Gerenciamento de cardapio")
public class CardapioController {
    private final CardapioExternoApiService cardapioExternoApiService;

    public CardapioController(CardapioExternoApiService cardapioExternoApiService) {
        this.cardapioExternoApiService = cardapioExternoApiService;
    }

    @Operation(summary = "Listar items do cardápio")
    @GetMapping
    public ResponseEntity<List<ItemCardapioExternoDTO>> listar() {
        return new ResponseEntity<>(cardapioExternoApiService.recuperarCardapio(), HttpStatus.OK);
    }

    @Operation(summary = "Recuperar item do cardápio")
    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioExternoDTO> recuperar(@PathVariable Long id) {
        return new ResponseEntity<>(cardapioExternoApiService.recuperarCardapioItem(id), HttpStatus.OK);
    }
}
