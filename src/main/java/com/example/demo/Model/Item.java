package com.example.demo.Model;

import com.example.demo.DTO.Item.ItemCreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString(exclude = "idItem")

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item", nullable = false)
    private Long idItem;
    @OneToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "subtotal", nullable = false , precision = 10 , scale = 2)
    private BigDecimal subtotal;

    public Item(ItemCreateDTO dto, Produto produto, Pedido pedido) {
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = dto.quantidade();
        this.subtotal = dto.subtotal();
    }
}
