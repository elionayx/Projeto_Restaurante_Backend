package com.example.restaurante.controller;

import com.example.restaurante.dto.ItemCardapioDTO;
import com.example.restaurante.mapper.ItemCardapioMapper;
import com.example.restaurante.model.ItemCardapio;
import com.example.restaurante.service.ItemCardapioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cardapio")
@Tag(name = "Itens do Cardápio", description = "Gerenciamento dos itens do cardápio")
public class ItemCardapioController {

    private final ItemCardapioService service;

    public ItemCardapioController(ItemCardapioService service) {
        this.service = service;
    }

    @Operation(
            summary = "Cadastrar um item no cardápio",
            description = "Cria um novo item no cardápio.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Item adicionado ao cardápio com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemCardapioDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<ItemCardapioDTO> cadastrar(@RequestBody ItemCardapioDTO dto) {
        ItemCardapio item = ItemCardapioMapper.toEntity(dto);
        ItemCardapio salvo = service.cadastrar(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(ItemCardapioMapper.toDTO(salvo));
    }

    @Operation(
            summary = "Listar todos os itens do cardápio",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de itens do cardápio",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemCardapioDTO.class)))
            }
    )
    @GetMapping
    public List<ItemCardapioDTO> listar() {
        return service.listarTodos().stream()
                .map(ItemCardapioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "Buscar um item do cardápio pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Item encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemCardapioDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ItemCardapioDTO buscarPorId(@PathVariable Long id) {
        return ItemCardapioMapper.toDTO(service.buscarPorId(id));
    }

    @Operation(
            summary = "Atualizar um item do cardápio",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Item atualizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemCardapioDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    @PutMapping("/{id}")
    public ItemCardapioDTO atualizar(@PathVariable Long id, @RequestBody ItemCardapioDTO dto) {
        dto.setId(id);
        ItemCardapio atualizado = service.atualizar(ItemCardapioMapper.toEntity(dto));
        return ItemCardapioMapper.toDTO(atualizado);
    }

    @Operation(
            summary = "Remover um item do cardápio pelo ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Item removido com sucesso", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
