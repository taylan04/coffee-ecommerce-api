package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString(exclude = "idProduto")

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto", nullable = false , length = 100)
    private Long idProduto;
    @Column(name = "titulo", nullable = false , length = 100)
    private String titulo;
    @Column(name = "descricao", nullable = false , length = 1024)
    private String descricao;
    @Column(name = "categoria", nullable = false , length = 100)
    private String categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Variante> variantes = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagemProduto> imagens = new ArrayList<>();
}
