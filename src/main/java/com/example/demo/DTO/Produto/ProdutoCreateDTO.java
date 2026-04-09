package com.example.demo.DTO.Produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProdutoCreateDTO (
        @NotBlank(message = "Título deve ser válido.")
        String titulo,
        @NotBlank(message = "Descrição deve ser válida.")
        String descricao,
        @NotBlank(message = "Categoria deve ser válida.")
        String categoria,
        List<ImagemProdutoCreateDTO> imagens,
        @NotEmpty(message = "O produto precisa ter pelo menos uma variante.")
        List<VarianteCreateDTO> variantes

) {}

