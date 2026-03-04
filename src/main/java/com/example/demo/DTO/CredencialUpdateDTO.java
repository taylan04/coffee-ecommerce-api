package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;

public record CredencialUpdateDTO(
        String email,
        String senha
) { }
