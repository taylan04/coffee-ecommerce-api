package com.example.demo.DTO;

import com.example.demo.Model.Credencial;

public record CredencialCreateDTO(
        String email,
        String senha
) {}
