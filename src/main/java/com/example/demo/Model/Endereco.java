package com.example.demo.Model;

import com.example.demo.DTO.Endereco.EnderecoCreateDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "endereco")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString(exclude = "idEndereco")

public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco", nullable = false)
    private Long idEndereco;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario usuario;
    @Column(name = "logradouro", nullable = false , length = 150)
    private String logradouro;
    @Column(name = "numero", nullable = false)
    private Integer numero;
    @Column(name = "complemento", nullable = true , length = 100)
    private String complemento;
    @Column(name = "referencia", nullable = true , length = 100)
    private String referencia;
    @Column(name = "bairro", nullable = false , length = 100)
    private String bairro;
    @Column(name = "cep", nullable = false , length = 9)
    private String cep;
    @Column(name = "cidade", nullable = false , length = 50)
    private String cidade;
    @Column(name = "estado", nullable = false , length = 50)
    private String estado;
    @Column(name = "pais", nullable = false , length = 50)
    private String pais;

    public Endereco(EnderecoCreateDTO dto, Usuario usuario) {
        this.usuario = usuario;
        this.logradouro = dto.logradouro();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.referencia = dto.referencia();
        this.bairro = dto.bairro();
        this.cep = dto.cep();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.pais = dto.pais();
    }
}
