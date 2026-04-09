package com.example.demo.Controller;

import com.example.demo.DTO.Credencial.CredencialDTO;
import com.example.demo.DTO.Credencial.CredencialUpdateDTO;
import com.example.demo.Service.CredencialService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credencial")
public class CredencialController {

    // @RequestBody converte o JSON enviado na requisição em um objeto Java
    // @PathVariable pega o valor que vem na URL (ex: /credenciais/5 → id = 5)

    private final CredencialService credencialService;

    public CredencialController(CredencialService credencialService) {
        this.credencialService = credencialService;
    }

    @GetMapping("/{id}")
    public CredencialDTO findById(@PathVariable Long id) {
        return credencialService.findById(id);
    }

    @GetMapping
    public List<CredencialDTO> findAll() {
        return credencialService.findAll();
    }

    @PatchMapping("/{id}")
    public CredencialDTO update(@PathVariable Long id, @RequestBody @Valid CredencialUpdateDTO credencial) {
        return credencialService.update(id, credencial);
    }

    //implementar autenticação de delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        credencialService.delete(id);
    }


}

