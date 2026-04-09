package com.example.demo.DTO.Produto;

import com.example.demo.Model.AtributoVariante;

public record AtributoVarianteDTO(
        Long id,
        String nomeAtributo,
        String valorAtributo
) {
    public AtributoVarianteDTO(AtributoVariante atributo) {
        this(
                atributo.getId(),
                atributo.getNomeAtributo(),
                atributo.getValorAtributo()
        );
    }
}

