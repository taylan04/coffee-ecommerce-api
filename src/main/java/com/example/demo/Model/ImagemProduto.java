package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagens_produto")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString(exclude = "id")
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_variante", nullable = true)
    private Variante variante;

    @Column(name = "url_imagem", nullable = false, length = 1024)
    private String urlImagem;

    @Column(name = "ordem", nullable = false)
    private Integer ordem;
}

