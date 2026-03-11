package com.example.demo.Service.Implement;

import com.example.demo.DTO.Usuario.UsuarioCreateDTO;
import com.example.demo.DTO.Usuario.UsuarioDTO;
import com.example.demo.DTO.Usuario.UsuarioUpdateDTO;
import com.example.demo.Exception.RecursoNaoEncontradoExcecao;
import com.example.demo.Model.Usuario;
import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.Service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Usuario não encontrado"));

        return new UsuarioDTO(usuario);
    }

    @Override
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        List <UsuarioDTO> usuariosDTOS = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuariosDTOS.add(new UsuarioDTO(usuario));
        }

        return usuariosDTOS;
    }

    @Override
    public UsuarioDTO save(UsuarioCreateDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setSobrenome(dto.sobrenome());
        usuario.setTelefone(dto.telefone());

        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO update(Long id, UsuarioUpdateDTO dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Usuario não encontrado"));

        if (dto.nome() != null) {
            usuario.setNome(dto.nome());
        }

        if (dto.sobrenome() != null) {
            usuario.setSobrenome(dto.sobrenome());
        }

        if (dto.telefone() != null) {
            usuario.setTelefone(dto.telefone());
        }

        if (dto.aniversario() != null) {
            usuario.setAniversario(dto.aniversario());
        }


        //já salva e retorna aqui mesmo oxe ta achando oq
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
