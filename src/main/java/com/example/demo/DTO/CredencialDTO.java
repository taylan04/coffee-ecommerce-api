package com.example.demo.DTO;

import com.example.demo.Model.Credencial;
import java.util.List;

public record CredencialDTO(
        Long id,
        String email,
        String nome,
        String sobrenome,
        List<String> roles
) {
    //construtor auxiliar para converter a o objeto credencial no DTO
    public CredencialDTO(Credencial credencial) {
        this(
                credencial.getIdCredencial(),
                credencial.getEmail(),
                //um ternário, e o usuário vir null ( talvez impossível ), ele fica null mesmo, se não pega o nome e sobrenome
                credencial.getUsuario() != null ? credencial.getUsuario().getNome() : null,
                credencial.getUsuario() != null ? credencial.getUsuario().getSobrenome() : null,
                credencial.getRoles()
        );
    }
}