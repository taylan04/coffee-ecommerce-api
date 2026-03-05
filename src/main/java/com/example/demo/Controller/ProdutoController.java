package com.example.demo.Controller;

import com.example.demo.DTO.ProdutoCreateDTO;
import com.example.demo.DTO.ProdutoDTO;
import com.example.demo.DTO.ProdutoUpdateDTO;
import com.example.demo.Model.Produto;
import com.example.demo.Service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.basic.BasicMenuItemUI;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    public ProdutoDTO findById(@PathVariable Long id) {
        return produtoService.findById(id);
    }

    @GetMapping
    public List<ProdutoDTO> findAll() {
        return produtoService.findAll();
    }

    @PostMapping
    public ProdutoDTO create(@RequestBody ProdutoCreateDTO produto) {
        return produtoService.save(produto);
    }

    @PatchMapping("/{id}")
    public ProdutoDTO update(@PathVariable Long id, @RequestBody ProdutoUpdateDTO produto) {
        return produtoService.update(id, produto);
    }

    //implementar autenticação de delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produtoService.delete(id);
    }

}
