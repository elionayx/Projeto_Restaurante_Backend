package com.example.restaurante.service;

import com.example.restaurante.dto.ClienteDTO;
import com.example.restaurante.model.Cliente;
import com.example.restaurante.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criarCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setCpf(dto.getCpf());

        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado com ID: " + id));
    }

    public void deletar(Long id) {
        Cliente cliente = this.buscarPorId(id);
        if (cliente != null) {
            this.clienteRepository.delete(cliente);
        }

    }
}
