package com.example.restaurante.service;

import com.example.restaurante.dto.CriarPedidoDTO;
import com.example.restaurante.dto.ItemPedidoRequestDTO;
import com.example.restaurante.model.*;
import com.example.restaurante.repository.ItemCardapioRepository;
import com.example.restaurante.repository.ItemPedidoRepository;
import com.example.restaurante.repository.MesaRepository;
import com.example.restaurante.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final MesaRepository mesaRepository;
    private final ItemCardapioRepository itemCardapioRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         MesaRepository mesaRepository,
                         ItemCardapioRepository itemCardapioRepository,
                         ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.mesaRepository = mesaRepository;
        this.itemCardapioRepository = itemCardapioRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Transactional
    public Pedido criarPedido(CriarPedidoDTO dto) {
        Mesa mesa = mesaRepository.findById(dto.getMesaId())
                .orElseThrow(() -> new EntityNotFoundException("Mesa não encontrada."));

        Pedido pedido = new Pedido();
        pedido.setMesa(mesa);
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(StatusPedido.ABERTO);

        List<ItemPedido> itens = new ArrayList<>();
        double total = 0.0;

        for (ItemPedidoRequestDTO itemDto : dto.getItens()) {
            ItemCardapio itemCardapio = itemCardapioRepository.findById(itemDto.getItemCardapioId())
                    .orElseThrow(() -> new EntityNotFoundException("Item do cardápio não encontrado."));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setItemCardapio(itemCardapio);
            itemPedido.setPedido(pedido);
            itemPedido.setQuantidade(itemDto.getQuantidade());

            itens.add(itemPedido);

            total += itemCardapio.getPreco() * itemDto.getQuantidade();
        }

        pedido.setValorTotal(total);
        pedido.setItens(itens);

        mesa.setStatus(StatusMesa.OCUPADA);
        mesaRepository.save(mesa);

        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itens);

        return pedido;
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado."));
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    @Transactional
    public Pedido atualizarStatus(Long id, StatusPedido status) {
        Pedido pedido = buscarPorId(id);
        pedido.setStatus(status);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido finalizarPedido(Long id, FormaPagamento pagamento) {
        Pedido pedido = buscarPorId(id);

        if (pedido.getStatus() != StatusPedido.ENTREGUE) {
            throw new IllegalStateException("Pedido não pode ser finalizado nesse status.");
        }

        pedido.setStatus(StatusPedido.FINALIZADO);
        pedido.setFormaPagamento(pagamento);

        Mesa mesa = pedido.getMesa();
        List<Pedido> pedidosDaMesa = pedidoRepository.findByMesaId(mesa.getId());

        boolean existemPedidosAbertos = pedidosDaMesa.stream()
                .anyMatch(p -> !p.getId().equals(id) && p.getStatus() != StatusPedido.FINALIZADO);

        if (!existemPedidosAbertos) {
            mesa.setStatus(StatusMesa.DISPONIVEL);
            mesaRepository.save(mesa);
        }

        return pedidoRepository.save(pedido);
    }
}
