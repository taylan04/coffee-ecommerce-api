package com.example.demo.DTO.Produto;

import com.example.demo.Model.Produto;

import java.util.ArrayList;
import java.util.List;

public record ProdutoDTO (
        Long id,
        String titulo,
        String descricao,
        String categoria,
        List<ImagemProdutoDTO> imagens,
        List<VarianteDTO> variantes
) {
    public ProdutoDTO(Produto produto) {
        this(
                produto.getIdProduto(),
                produto.getTitulo(),
                produto.getDescricao(),
                produto.getCategoria(),
                produto.getImagens() != null
                        ? produto.getImagens().stream().map(ImagemProdutoDTO::new).toList()
                        : new ArrayList<>(),
                produto.getVariantes() != null
                        ? produto.getVariantes().stream().map(VarianteDTO::new).toList()
                        : new ArrayList<>()
        );
    }
}
