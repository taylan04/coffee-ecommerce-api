package com.example.demo.DTO.Cupom;

import com.example.demo.Model.enumCupom;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CupomCreateDTO (
        @NotBlank(message = "Código do cupom precisa ser válido.")
        String codigo,
        @NotNull(message = "O valor do cupom não pode ser nulo") @Min(value = 1, message = "O valor precisa ser maior que zero.")
        BigDecimal valor,
        @NotBlank(message = "O tipo do cupom precisa ser válido.")
        String tipo,
        @NotNull(message = "O estado do cupom precisa ser válido.")
        enumCupom estado
) {}
