package com.example.demo.DTO.Item;

import com.example.demo.Model.Item;

import java.math.BigDecimal;

public record ItemDTO (
        Long id,
        Long idProduto,
        Long idVariante,
        Long idPedido,
        Integer quantidade,
        BigDecimal subtotal
) {
    //esse construtor é o que permite fazer isso: return new ItemDTO(item);
    public ItemDTO(Item item) {
        this(
                item.getIdItem(),
                item.getVariante().getProduto().getIdProduto(),
                item.getVariante().getIdVariante(),
                item.getPedido().getIdPedido(),
                item.getQuantidade(),
                item.getSubtotal()
        );
    }
}