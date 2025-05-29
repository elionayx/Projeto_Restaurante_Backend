package com.example.restaurante.service;

import com.example.restaurante.dto.PedidoDTO;
import com.example.restaurante.model.Cliente;
import com.example.restaurante.model.ItemCardapio;
import com.example.restaurante.model.Pedido;
import com.example.restaurante.model.PedidoStatus;
import com.example.restaurante.repository.ClienteRepository;
import com.example.restaurante.repository.ItemCardapioRepository;
import com.example.restaurante.repository.PedidoRepository;
import com.example.restaurante.service.integration.CardapioExternoApiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ItemCardapioRepository itemCardapioRepository;
    private final CardapioExternoApiService cardapioExternoApiService;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         ItemCardapioRepository itemCardapioRepository,
                         CardapioExternoApiService cardapioExternoApiService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.itemCardapioRepository = itemCardapioRepository;
        this.cardapioExternoApiService = cardapioExternoApiService;
    }

    public void realizarPedido(PedidoDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado"));

        List<ItemCardapio> itens = cardapioExternoApiService.recuperarCardapioFiltrado(dto.getItens());
        if (itens.size() != dto.getItens().size()) {
            throw new IllegalArgumentException("Alguns itens do pedido não foram encontrados");
        }

        List<ItemCardapio> salvos = this.itemCardapioRepository.saveAll(itens);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setItens(salvos);
        pedido.setStatus(PedidoStatus.RECEBIDO); // status inicial

        pedidoRepository.save(pedido);
    }

    public Pedido buscarPedido(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido não encontrado"));
    }

    public void atualizarStatus(Long id, String status) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido não encontrado"));

        pedido.setStatus(PedidoStatus.valueOf(status.toUpperCase()));

        pedidoRepository.save(pedido);
    }
}
