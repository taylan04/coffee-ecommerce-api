package com.example.demo.Model;

import com.example.demo.DTO.Auth.GoogleUserData;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "credencial")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString(exclude = "idCredencial")

// como eu usei findById, preciso rever nas entidades o conceito de:
// @OneToOne(fetch = FetchType.LAZY) ou FetchType.EAGER

public class Credencial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credencial", nullable = false)
    private Long idCredencial;
    //fetch = FetchType.EAGER = sempre que buscar credencial, as roles vão vir junto
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "credencial_roles", joinColumns = @JoinColumn(name = "id_credencial"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>(List.of(Role.USER));
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @Column(name = "email", unique = true, nullable = false, length = 80)
    private String email;
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;
    @Column(name = "criado_em", nullable = false)
    @CreationTimestamp // seta o .now() automaticamente quando criar
    private LocalDateTime criadoEm;
    @Column(name = "atualizado_em", nullable = false)
    @UpdateTimestamp // atualiza a data sempre que houver um update
    private LocalDateTime atualizadoEm;
    @Enumerated(EnumType.STRING)
    @Column(name = "provedor", nullable = false)
    private AuthProvedor provedor = AuthProvedor.LOCAL;
}
