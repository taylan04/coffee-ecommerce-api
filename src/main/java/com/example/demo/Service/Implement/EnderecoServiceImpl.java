package com.example.demo.Service.Implement;

import com.example.demo.DTO.Endereco.EnderecoCreateDTO;
import com.example.demo.DTO.Endereco.EnderecoDTO;
import com.example.demo.DTO.Endereco.EnderecoUpdateDTO;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Endereco;
import com.example.demo.Model.Usuario;
import com.example.demo.Repository.EnderecoRepository;
import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.Service.EnderecoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository) {
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public EnderecoDTO findById(Long id) {
        //estou pensando se coloco HttpStatus em todos, pois retorna 404, 500 ou 200
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado"));

        return new EnderecoDTO(endereco);
    }

    @Override
    public List<EnderecoDTO> findAll() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        List<EnderecoDTO> enderecosDTOS = new ArrayList<>();
        for (Endereco endereco : enderecos) {
            enderecosDTOS.add(new EnderecoDTO(endereco));
        }

        return enderecosDTOS;
    }

    @Override
    public EnderecoDTO save(EnderecoCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado"));
        Endereco endereco = new Endereco(dto, usuario);
        return new EnderecoDTO(enderecoRepository.save(endereco));
    }

    @Override
    public EnderecoDTO update(Long id, EnderecoUpdateDTO novo) {
        Endereco existente = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado"));

        if (novo.logradouro() != null) {
            existente.setLogradouro(novo.logradouro());
        }

        if (novo.numero() != null) {
            existente.setNumero(novo.numero());
        }

        if (novo.complemento() != null) {
            existente.setComplemento(novo.complemento());
        }

        if (novo.bairro() != null) {
            existente.setBairro(novo.bairro());
        }

        if (novo.cidade() != null) {
            existente.setCidade(novo.cidade());
        }

        if (novo.estado() != null) {
            existente.setEstado(novo.estado());
        }

        if (novo.cep() != null) {
            existente.setCep(novo.cep());
        }

        if (novo.referencia() != null) {
            existente.setReferencia(novo.referencia());
        }

        if (novo.pais() != null) {
            existente.setPais(novo.pais());
        }

        return new EnderecoDTO(enderecoRepository.save(existente));
    }

    @Override
    public void delete(Long id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    public List<EnderecoDTO> buscarPorUsuario(Long idUsuario) {
        List<Endereco> enderecos = enderecoRepository.findByUsuarioIdUsuario(idUsuario);
        List<EnderecoDTO> enderecosDTOS = new ArrayList<>();
        for (Endereco endereco : enderecos) {
            enderecosDTOS.add(new EnderecoDTO(endereco));
        }
        return enderecosDTOS;
    }
}
