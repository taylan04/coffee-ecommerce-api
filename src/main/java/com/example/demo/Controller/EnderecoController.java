package com.example.demo.Controller;

import com.example.demo.DTO.Endereco.EnderecoCreateDTO;
import com.example.demo.DTO.Endereco.EnderecoDTO;
import com.example.demo.DTO.Endereco.EnderecoUpdateDTO;
import com.example.demo.Service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public EnderecoDTO create(@RequestBody @Valid EnderecoCreateDTO endereco) {
        return enderecoService.save(endereco);
    }

    @PatchMapping("/{id}")
    public EnderecoDTO update(@PathVariable Long id, @RequestBody @Valid EnderecoUpdateDTO endereco) {
        return enderecoService.update(id, endereco);
    }

    //implementar autenticação de delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        enderecoService.delete(id);
    }

}
