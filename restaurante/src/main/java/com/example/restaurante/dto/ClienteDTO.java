package com.example.restaurante.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema (description = "DTO para criar um cliente.")
public class ClienteDTO {

    @Schema (description = "Nome do Cliente", example = "Margarida Oliveira")
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @Schema (description = "Email do cliente", example = "margaridaoli@email.com")
    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @Schema (description = "CPF no formato: 000.000.000-00", example = "555.777.333.99")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato 000.000.000-00")
    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
