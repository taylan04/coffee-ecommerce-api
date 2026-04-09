package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString(exclude = "idPagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento", nullable = false)
    private Long idPagamento;

    @OneToOne
    @JoinColumn(name = "id_pedido", nullable = false, unique = true)
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private enumPagamento status;

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_pagamento", nullable = true)
    private LocalDateTime dataPagamento;
}

