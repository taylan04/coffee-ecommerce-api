package com.example.demo.Service.Implement;

import com.example.demo.DTO.Produto.ProdutoCreateDTO;
import com.example.demo.DTO.Produto.ProdutoDTO;
import com.example.demo.DTO.Produto.ProdutoUpdateDTO;
import com.example.demo.Exception.RecursoNaoEncontradoExcecao;
import com.example.demo.Model.Produto;
import com.example.demo.Repository.ProdutoRepository;
import com.example.demo.Service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public ProdutoDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Produto não encontrado"));

        return new ProdutoDTO(produto);
    }

    @Override
    public List<ProdutoDTO> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDTO> produtoDTOS = new ArrayList<>();
        for (Produto produto : produtos) {
            produtoDTOS.add(new ProdutoDTO(produto));
        }

        return produtoDTOS;
    }

    @Override
    public ProdutoDTO save(ProdutoCreateDTO dto) {
        Produto produto = new Produto(dto);
        produtoRepository.save(produto);
        return new ProdutoDTO(produto);
    }

    @Override
    public ProdutoDTO update(Long id, ProdutoUpdateDTO novo) {

        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Produto não encontrado"));

        if (novo.titulo() != null) {
            existente.setTitulo(novo.titulo());
        }

        if (novo.descricao() != null) {
            existente.setDescricao(novo.descricao());
        }

        if (novo.quantidade() != null) {
            existente.setQuantidade(novo.quantidade());
        }

        if (novo.imagemUrl() != null) {
            existente.setImagemUrl(novo.imagemUrl());
        }

        if (novo.tipo() != null) {
            existente.setTipo(novo.tipo());
        }

        if (novo.preco() != null) {
            existente.setPreco(novo.preco());
        }

        return new ProdutoDTO(produtoRepository.save(existente));
    }

    @Override
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }
}
