package com.example.demo.DTO;

import com.example.demo.Model.Credencial;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CredencialCreateDTO(
        //CARA ISSO É SURREAL DE LEGAL, @NotBlank FAZ COM QUE O CAMPO NÃO PERMITA NULL, "", OU "  "
        @NotBlank(message = "Informe um email válido") @Email
        String email,
        @NotBlank(message = "Informe um email válido.")
        String senha
) {}
