package com.example.demo.Controller;

import com.example.demo.DTO.Endereco.EnderecoCreateDTO;
import com.example.demo.DTO.Endereco.EnderecoDTO;
import com.example.demo.DTO.Endereco.EnderecoUpdateDTO;
import com.example.demo.DTO.Pagamento.PagamentoDTO;
import com.example.demo.DTO.Pagamento.PagamentoUpdateDTO;
import com.example.demo.Service.EnderecoService;
import com.example.demo.Service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping("/{id}")
    public PagamentoDTO findById(@PathVariable Long id) {
        return pagamentoService.findById(id);
    }

    @GetMapping
    public List<PagamentoDTO> findAll() {
        return pagamentoService.findAll();
    }

    @PatchMapping("/{id}")
    public PagamentoDTO update(@PathVariable Long id, @RequestBody @Valid PagamentoUpdateDTO pagamento) {
        return pagamentoService.update(id, pagamento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pagamentoService.delete(id);
    }

}