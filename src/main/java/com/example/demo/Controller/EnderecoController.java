package com.example.demo.Controller;

import com.example.demo.DTO.EnderecoCreateDTO;
import com.example.demo.DTO.EnderecoDTO;
import com.example.demo.DTO.EnderecoUpdateDTO;
import com.example.demo.Model.Credencial;
import com.example.demo.Model.Endereco;
import com.example.demo.Service.EnderecoService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/{id}")
    public EnderecoDTO findById(@PathVariable Long id) {
        return enderecoService.findById(id);
    }

    @GetMapping
    public List<EnderecoDTO> findAll() {
        return enderecoService.findAll();
    }

    @PostMapping
    public EnderecoDTO create(@RequestBody EnderecoCreateDTO endereco) {
        return enderecoService.save(endereco);
    }

    @PatchMapping("/{id}")
    public EnderecoDTO update(@PathVariable Long id, @RequestBody EnderecoUpdateDTO endereco) {
        return enderecoService.update(id, endereco);
    }

    //implementar autenticação de delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        enderecoService.delete(id);
    }

}
