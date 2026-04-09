package com.example.demo.DTO.Produto;

import com.example.demo.Model.ImagemProduto;

public record ImagemProdutoDTO(
        Long id,
        String urlImagem,
        Integer ordem,
        Long idVariante
) {
    public ImagemProdutoDTO(ImagemProduto imagem) {
        this(
                imagem.getId(),
                imagem.getUrlImagem(),
                imagem.getOrdem(),
                imagem.getVariante() != null ? imagem.getVariante().getIdVariante() : null
        );
    }
}

