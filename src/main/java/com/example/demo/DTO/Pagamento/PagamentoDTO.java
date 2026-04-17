package com.example.demo.DTO.Pagamento;

import com.example.demo.Model.Pagamento;
import com.example.demo.Model.enumPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoDTO(
        Long id,
        Long idPedido,
        enumPagamento status,
        BigDecimal valor,
        LocalDateTime dataCriacao,
        LocalDateTime dataPagamento
) {
    public PagamentoDTO(Pagamento pagamento) {
        this(
                pagamento.getIdPagamento(),
                pagamento.getPedido().getIdPedido(),
                pagamento.getStatus(),
                pagamento.getValor(),
                pagamento.getDataCriacao(),
                pagamento.getDataPagamento()
        );
    }
}