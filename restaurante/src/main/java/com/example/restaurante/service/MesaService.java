package com.example.restaurante.service;

import com.example.restaurante.model.Mesa;
import com.example.restaurante.model.StatusMesa;
import com.example.restaurante.repository.MesaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    public Mesa cadastrar(Mesa mesa) {
        if (mesaRepository.existsByNumero(mesa.getNumero())) {
            throw new IllegalArgumentException("Já existe uma mesa com esse número.");
        }
        mesa.setStatus(StatusMesa.DISPONIVEL);
        return mesaRepository.save(mesa);
    }

    public Mesa buscarPorId(Long id) {
        return mesaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mesa não encontrada."));
    }

    public List<Mesa> listarTodas() {
        return mesaRepository.findAll();
    }

    public void remover(Long id) {
        Mesa mesa = buscarPorId(id);
        mesaRepository.delete(mesa);
    }

    public Mesa atualizarStatus(Long id, StatusMesa status) {
        Mesa mesa = buscarPorId(id);
        mesa.setStatus(status);
        return mesaRepository.save(mesa);
    }
}
