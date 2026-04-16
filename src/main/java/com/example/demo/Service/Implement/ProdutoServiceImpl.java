package com.example.demo.Service.Implement;

import com.example.demo.DTO.Produto.AtributoVarianteCreateDTO;
import com.example.demo.DTO.Produto.ImagemProdutoCreateDTO;
import com.example.demo.DTO.Produto.ProdutoCreateDTO;
import com.example.demo.DTO.Produto.ProdutoDTO;
import com.example.demo.DTO.Produto.ProdutoUpdateDTO;
import com.example.demo.DTO.Produto.VarianteCreateDTO;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.AtributoVariante;
import com.example.demo.Model.ImagemProduto;
import com.example.demo.Model.Produto;
import com.example.demo.Model.Variante;
import com.example.demo.Repository.ProdutoRepository;
import com.example.demo.Service.ProdutoService;
import jakarta.transaction.Transactional;
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
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

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
    @Transactional
    public ProdutoDTO save(ProdutoCreateDTO dto) {
        Produto produto = new Produto();
        produto.setTitulo(dto.titulo());
        produto.setDescricao(dto.descricao());
        produto.setCategoria(dto.categoria());

        if (dto.variantes() != null) {
            for (VarianteCreateDTO varianteDTO : dto.variantes()) {
                Variante variante = new Variante();
                variante.setProduto(produto);
                variante.setSku(varianteDTO.sku());
                variante.setPreco(varianteDTO.preco());
                variante.setEstoque(varianteDTO.estoque());

                if (varianteDTO.atributos() != null) {
                    for (AtributoVarianteCreateDTO atributoDTO : varianteDTO.atributos()) {
                        AtributoVariante atributo = new AtributoVariante();
                        atributo.setVariante(variante);
                        atributo.setNomeAtributo(atributoDTO.nomeAtributo());
                        atributo.setValorAtributo(atributoDTO.valorAtributo());
                        variante.getAtributos().add(atributo);
                    }
                }

                produto.getVariantes().add(variante);

                if (varianteDTO.imagens() != null) {
                    for (ImagemProdutoCreateDTO imagemDTO : varianteDTO.imagens()) {
                        ImagemProduto imagem = new ImagemProduto();
                        imagem.setProduto(produto);
                        imagem.setVariante(variante);
                        imagem.setUrlImagem(imagemDTO.urlImagem());
                        imagem.setOrdem(imagemDTO.ordem());
                        produto.getImagens().add(imagem);
                    }
                }
            }
        }

        if (dto.imagens() != null) {
            for (ImagemProdutoCreateDTO imagemDTO : dto.imagens()) {
                ImagemProduto imagem = new ImagemProduto();
                imagem.setProduto(produto);
                imagem.setVariante(null);
                imagem.setUrlImagem(imagemDTO.urlImagem());
                imagem.setOrdem(imagemDTO.ordem());
                produto.getImagens().add(imagem);
            }
        }

        Produto salvo = produtoRepository.save(produto);
        return new ProdutoDTO(salvo);
    }

    @Override
    public ProdutoDTO update(Long id, ProdutoUpdateDTO novo) {

        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if (novo.titulo() != null) {
            existente.setTitulo(novo.titulo());
        }

        if (novo.descricao() != null) {
            existente.setDescricao(novo.descricao());
        }

        if (novo.categoria() != null) {
            existente.setCategoria(novo.categoria());
        }

        return new ProdutoDTO(produtoRepository.save(existente));
    }

    @Override
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }
}
