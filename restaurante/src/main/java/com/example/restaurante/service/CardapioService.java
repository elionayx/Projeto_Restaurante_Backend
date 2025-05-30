package com.example.restaurante.service;

import com.example.restaurante.dto.ItemCardapioDTO;
import com.example.restaurante.mapper.ItemCardapioMapper;
import com.example.restaurante.model.ItemCardapio;
import com.example.restaurante.service.integration.CardapioExternoApiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardapioService {
    private final CardapioExternoApiService cardapioExternoApiService;
    private final ItemCardapioService itemCardapioService;

    public CardapioService(CardapioExternoApiService cardapioExternoApiService,
                           ItemCardapioService itemCardapioService) {
        this.cardapioExternoApiService = cardapioExternoApiService;
        this.itemCardapioService = itemCardapioService;
    }

    public List<ItemCardapioDTO> sincronizar() {
        List<ItemCardapioDTO> dados = cardapioExternoApiService.sincronizar();
        List<ItemCardapio> dadosMapeados = new ArrayList<>();

        for (ItemCardapioDTO item: dados) {
            dadosMapeados.add(ItemCardapioMapper.toEntity(item));
        }
        dados = new ArrayList<>();

        List<ItemCardapio> dadosSalvos = this.itemCardapioService.cadastrarTudo(dadosMapeados);

        for (ItemCardapio dadoSalvo: dadosSalvos) {
            dados.add(ItemCardapioMapper.toDTO(dadoSalvo));
        }

        return dados;


    }
}
