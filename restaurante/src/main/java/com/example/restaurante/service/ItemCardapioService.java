package com.example.restaurante.service;

import com.example.restaurante.model.ItemCardapio;
import com.example.restaurante.repository.ItemCardapioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCardapioService {

    private final ItemCardapioRepository itemCardapioRepository;

    public ItemCardapioService(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public ItemCardapio cadastrar(ItemCardapio item) {
        return itemCardapioRepository.save(item);
    }

    public List<ItemCardapio> cadastrarTudo(List<ItemCardapio> itens) {
        return itemCardapioRepository.saveAll(itens);
    }

    public List<ItemCardapio> listarTodos() {
        return itemCardapioRepository.findAll();
    }

    public ItemCardapio buscarPorId(Long id) {
        return itemCardapioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item do cardápio não encontrado."));
    }

    public ItemCardapio atualizar(ItemCardapio item) {
        buscarPorId(item.getId());
        return itemCardapioRepository.save(item);
    }

    public void remover(Long id) {
        ItemCardapio item = buscarPorId(id);
        itemCardapioRepository.delete(item);
    }
}
