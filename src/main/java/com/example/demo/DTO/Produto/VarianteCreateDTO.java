package com.example.demo.DTO.Produto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record VarianteCreateDTO(
        @NotBlank(message = "SKU deve ser válido.")
        String sku,
        @NotNull(message = "O preço não pode ser nulo.") @Min(value = 1, message = "O preço deve ser maior que zero.")
        BigDecimal preco,
        @NotNull(message = "O estoque não pode ser nulo.") @Min(value = 0, message = "O estoque deve ser maior ou igual a zero.")
        Integer estoque,
        List<AtributoVarianteCreateDTO> atributos,
        List<ImagemProdutoCreateDTO> imagens
) {}

