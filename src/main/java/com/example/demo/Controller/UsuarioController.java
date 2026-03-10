package com.example.demo.Controller;

import com.example.demo.DTO.Usuario.UsuarioCreateDTO;
import com.example.demo.DTO.Usuario.UsuarioDTO;
import com.example.demo.DTO.Usuario.UsuarioUpdateDTO;
import com.example.demo.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public UsuarioDTO findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @GetMapping
    public List<UsuarioDTO> findAll() {
        return usuarioService.findAll();
    }

    @PostMapping
    public UsuarioDTO create(@RequestBody @Valid UsuarioCreateDTO dto) {
        return usuarioService.save(dto);
    }

    @PutMapping("/{id}")
    public UsuarioDTO update(@PathVariable Long id, @RequestBody @Valid UsuarioUpdateDTO dto) {
        return usuarioService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}