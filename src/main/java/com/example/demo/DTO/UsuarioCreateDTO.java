package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UsuarioCreateDTO(
        @NotBlank(message = "O nome precisa ser válido.")
        String nome,
        @NotBlank(message = "Sobrenome precisa ser válido.")
        String sobrenome,
        @NotBlank(message = "Telefone precisa ser válido.")
        String telefone,
        LocalDate aniversario
) {}