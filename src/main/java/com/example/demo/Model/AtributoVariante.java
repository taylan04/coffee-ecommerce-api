package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "atributos_variante")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString(exclude = "id")
public class AtributoVariante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_variante", nullable = false)
    private Variante variante;

    @Column(name = "nome_atributo", nullable = false, length = 100)
    private String nomeAtributo;

    @Column(name = "valor_atributo", nullable = false, length = 255)
    private String valorAtributo;
}

