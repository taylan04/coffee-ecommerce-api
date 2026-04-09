package com.example.demo.Model;

import com.example.demo.DTO.Pedido.PedidoCreateDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString(exclude = "idPedido")

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido", nullable = false)
    private Long idPedido;
    @ManyToOne
    @JoinColumn(name = "id_usuario" , nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_cupom" , nullable = true)
    private Cupom cupom;
    @Column(name = "estado", nullable = false , length = 50)
    private String estado;
    @Column(name = "desconto", nullable = true , precision = 10 , scale = 2)
    private BigDecimal desconto;
    @Column(name = "data_pedido", nullable = false)
    @CreationTimestamp
    private LocalDateTime dataPedido;
    @Column(name = "valor_total", nullable = true , precision = 10 , scale = 2)
    private BigDecimal valorTotal;
    @Column(name = "valor_final", nullable = false , precision = 10 , scale = 2)
    private BigDecimal valorFinal;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Pagamento pagamento;

    public Pedido(PedidoCreateDTO dto, Cupom cupom, Usuario usuario) {
        this.usuario = usuario;
        this.cupom = cupom;
        this.estado = dto.estado();
        this.desconto = dto.desconto();
        this.dataPedido = dto.dataPedido();
        this.valorTotal = dto.valorTotal();
        this.valorFinal = dto.valorFinal();
    }
}
