package com.example.demo.Controller;

import com.example.demo.DTO.Endereco.EnderecoDTO;
import com.example.demo.DTO.Pedido.PedidoDTO;
import com.example.demo.DTO.Usuario.UsuarioCreateDTO;
import com.example.demo.DTO.Usuario.UsuarioDTO;
import com.example.demo.DTO.Usuario.UsuarioUpdateDTO;
import com.example.demo.Service.EnderecoService;
import com.example.demo.Service.PedidoService;
import com.example.demo.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EnderecoService enderecoService;
    private final PedidoService pedidoService;

    public UsuarioController(UsuarioService usuarioService, EnderecoService enderecoService, PedidoService pedidoService) {
        this.usuarioService = usuarioService;
        this.enderecoService = enderecoService;
        this.pedidoService = pedidoService;
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

    @GetMapping("/{id}/enderecos")
    public List<EnderecoDTO> findAllEndereco(@PathVariable Long id) {
        return enderecoService.buscarPorUsuario(id);
    }

    @GetMapping("/{id}/pedidos")
    public List<PedidoDTO> findAllPedido(@PathVariable Long id) {
        return pedidoService.buscarPorUsuario(id);
    }
}