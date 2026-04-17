package com.example.demo.DTO.Pagamento;

import com.example.demo.Model.Pedido;
import com.example.demo.Model.enumPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoCreateDTO  (
        @NotNull(message = "O Id do Pedido não pode ser nulo")
        Long idPedido,
        @NotNull(message = "O valor não pode ser nulo") @Min(value = 0, message = "O valor precisa ser maior ou igual a zero.")
        BigDecimal valor
) {}