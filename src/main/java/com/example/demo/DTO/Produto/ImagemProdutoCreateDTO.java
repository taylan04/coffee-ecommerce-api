package com.example.demo.DTO.Produto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ImagemProdutoCreateDTO(
        @NotBlank(message = "URL da imagem deve ser válida.")
        String urlImagem,
        @NotNull(message = "A ordem não pode ser nula.") @Min(value = 0, message = "A ordem deve ser maior ou igual a zero.")
        Integer ordem
) {}

