package com.example.demo.DTO.Pedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoCreateDTO(
        @NotNull(message = "O Id do Usuário não pode ser nulo")
        Long idUsuario,
        Long idCupom,
        @NotBlank(message = "O estado do pedido precisa ser válido.")
        String estado,
        BigDecimal desconto,
        @NotNull(message = "A data precisa ser válida.")
        LocalDateTime dataPedido,
        @NotNull(message = "O valor total não pode ser nulo") @Min(value = 0, message = "O valor precisa ser maior ou igual a zero.")
        BigDecimal valorTotal,
        @NotNull(message = "O valor total não pode ser nulo") @Min(value = 0, message = "O valor precisa ser maior ou igual a zero.")
        BigDecimal valorFinal)
{}
