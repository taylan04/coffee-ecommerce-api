package com.example.demo.DTO.Pedido;
import com.example.demo.Model.Pedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoDTO(
        Long id,
        Long idUsuario,
        Long idCupom,
        String estado,
        BigDecimal desconto,
        LocalDateTime dataPedido,
        BigDecimal valorTotal,
        BigDecimal valorFinal
) {
    //esse construtor é o que permite fazer isso: return new PedidoDTO(pedido);
    public PedidoDTO(Pedido pedido) {
        this(
                pedido.getIdPedido(),
                pedido.getUsuario().getIdUsuario(),
                pedido.getCupom() != null ? pedido.getCupom().getIdCupom() : null,
                pedido.getEstado(),
                pedido.getDesconto(),
                pedido.getDataPedido(),
                pedido.getValorTotal(),
                pedido.getValorFinal()
        );
    }
}