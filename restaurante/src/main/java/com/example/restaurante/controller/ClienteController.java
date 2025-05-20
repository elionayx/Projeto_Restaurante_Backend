package com.example.restaurante.controller;

import com.example.restaurante.dto.ClienteDTO;
import com.example.restaurante.model.Cliente;
import com.example.restaurante.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Gerenciamento de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Criar um novo cliente")
    @PostMapping
    public ResponseEntity<String> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        clienteService.criarCliente(clienteDTO);
        return new ResponseEntity<>("Cliente criado com sucesso", HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }
}
