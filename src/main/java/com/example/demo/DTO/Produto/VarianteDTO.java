package com.example.demo.DTO.Produto;

import com.example.demo.Model.Variante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record VarianteDTO(
        Long id,
        String sku,
        BigDecimal preco,
        Integer estoque,
        List<AtributoVarianteDTO> atributos,
        List<ImagemProdutoDTO> imagens
) {
    public VarianteDTO(Variante variante) {
        this(
                variante.getIdVariante(),
                variante.getSku(),
                variante.getPreco(),
                variante.getEstoque(),
                variante.getAtributos() != null
                        ? variante.getAtributos().stream().map(AtributoVarianteDTO::new).toList()
                        : new ArrayList<>(),
                new ArrayList<>()
        );
    }
}

