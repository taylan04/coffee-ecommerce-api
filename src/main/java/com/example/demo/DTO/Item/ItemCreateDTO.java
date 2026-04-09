package com.example.demo.DTO.Item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemCreateDTO (
        @NotNull(message = "O Id da Variante não pode ser nulo") Long idVariante,
        @NotNull(message = "O Id do Pedido não pode ser nulo") Long idPedido,
        @NotNull(message = "O valor da quantidade não pode ser nulo") @Min(value = 0, message = "O valor precisa ser maior ou igual a zero.") Integer quantidade,
        @NotNull(message = "O subtotal não pode ser nulo") @Min(value = 0, message = "O valor precisa ser maior ou igual a zero.") BigDecimal subtotal
)
{}
