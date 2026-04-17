package com.example.demo.DTO.Pagamento;

import com.example.demo.Model.enumPagamento;

import java.time.LocalDateTime;

public record PagamentoUpdateDTO (
    enumPagamento status,
    LocalDateTime dataPagamento
)
{}