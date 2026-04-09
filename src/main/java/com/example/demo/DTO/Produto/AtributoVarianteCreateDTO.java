package com.example.demo.DTO.Produto;

import jakarta.validation.constraints.NotBlank;

public record AtributoVarianteCreateDTO(
        @NotBlank(message = "Nome do atributo deve ser válido.")
        String nomeAtributo,
        @NotBlank(message = "Valor do atributo deve ser válido.")
        String valorAtributo
) {}

