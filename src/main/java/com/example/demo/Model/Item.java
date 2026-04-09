package com.example.demo.Model;

import com.example.demo.DTO.Item.ItemCreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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
    @ManyToOne
    @JoinColumn(name = "id_variante", nullable = false)
    private Variante variante;
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "subtotal", nullable = false , precision = 10 , scale = 2)
    private BigDecimal subtotal;

    public Item(ItemCreateDTO dto, Variante variante, Pedido pedido) {
        this.variante = variante;
        this.pedido = pedido;
        this.quantidade = dto.quantidade();
        this.subtotal = dto.subtotal();
    }
}
