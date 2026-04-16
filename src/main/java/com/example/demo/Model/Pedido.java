package com.example.demo.Model;

import com.example.demo.DTO.Pedido.PedidoCreateDTO;
import com.example.demo.DTO.Pedido.PedidoDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
    @JoinColumn(name = "id_endereco" , nullable = false)
    private Endereco endereco;
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
    @JoinColumn(name = "expresso" , nullable = false)
    private boolean expresso;
    @Column(name = "valor_total", nullable = true , precision = 10 , scale = 2)
    private BigDecimal valorTotal;
    @Column(name = "valor_final", nullable = false , precision = 10 , scale = 2)
    private BigDecimal valorFinal;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Pagamento pagamento;

    public Pedido(PedidoCreateDTO dto, Cupom cupom, Usuario usuario, Endereco endereco) {
        this.usuario = usuario;
        this.endereco = endereco;
        this.cupom = cupom;
        this.estado = dto.estado();
        this.desconto = dto.desconto();
        this.dataPedido = dto.dataPedido();
        this.valorTotal = dto.valorTotal();
        this.valorFinal = dto.valorFinal();
        this.expresso = verificarPedidoExpresso(dto.dataPedido(), endereco);
    }

    private boolean verificarPedidoExpresso(LocalDateTime data, Endereco endereco) {
        //Adicionar expressão para verificar Endereco: localidade no município do Rio de Janeiro
        boolean horarioExpresso = data.getHour() < 12;
        if (horarioExpresso) {
            return true;
        }
        return false;
    }
}
