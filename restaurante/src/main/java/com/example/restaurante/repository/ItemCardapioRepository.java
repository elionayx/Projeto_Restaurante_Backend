package com.example.restaurante.repository;

import com.example.restaurante.model.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
    Optional<ItemCardapio> findByIdExterno(Long idExterno);
}