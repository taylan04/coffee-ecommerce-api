package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "variantes")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString(exclude = "idVariante")
public class Variante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_variante", nullable = false)
    private Long idVariante;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(name = "sku", nullable = false, length = 100)
    private String sku;

    @Column(name = "preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "estoque", nullable = false)
    private Integer estoque;

    @OneToMany(mappedBy = "variante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AtributoVariante> atributos = new ArrayList<>();
}

