package com.example.demo.Service.Implement;

import com.example.demo.DTO.Credencial.CredencialCreateDTO;
import com.example.demo.DTO.Credencial.CredencialDTO;
import com.example.demo.DTO.Credencial.CredencialUpdateDTO;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.AuthProvedor;
import com.example.demo.Model.Credencial;
import com.example.demo.Repository.CredencialRepository;
import com.example.demo.Service.CredencialService;
import com.example.demo.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CredencialServiceImpl implements CredencialService {

    private final CredencialRepository credencialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CredencialServiceImpl(CredencialRepository credencialRepository, TokenService jwtService) {
        this.credencialRepository = credencialRepository;
    }

    //Esses @Override em cima dos métodos é pra dizer olha, estou usando esse método que está na interface

    // Uso findById porque getById pode retornar um proxy (objeto temporário
    // que só carrega dados ao acessar seus atributos). findById é mais seguro.

    @Override
    public CredencialDTO findById(Long id) {
        Credencial credencial = credencialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credencial não encontrado"));
        //.orElseThrow() faz com que o método retorno um objeto invés de um Optional

        return new CredencialDTO(credencial);
    }

    @Override
    public List<CredencialDTO> findAll() {
        List<Credencial> credenciais = credencialRepository.findAll();
        //obs: usei forEach primeiro mas esqueci que forEach não retorna nada, então nao posso usar aqui
        List<CredencialDTO> credenciaisDTOS = new ArrayList<>();
        for (Credencial credencial : credenciais ) {
            credenciaisDTOS.add(new CredencialDTO(credencial));
        }

        return credenciaisDTOS;
    }

    @Override
    public CredencialDTO save(CredencialCreateDTO dto) {
        Credencial credencial = new Credencial();
        credencial.setEmail(dto.email());
        String senhaCriptografada = passwordEncoder.encode(dto.senha());
        credencial.setSenha(senhaCriptografada);
        credencialRepository.save(credencial);
        return new CredencialDTO(credencial);
    }

    @Override
    public CredencialDTO update(Long id, CredencialUpdateDTO dto) {

        Credencial existente = credencialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credencial não encontrada"));

        if (existente.getProvedor() == AuthProvedor.GOOGLE) {
            throw new IllegalStateException("Usuários autenticados com Google não podem alterar credenciais.");
        }

        if (dto.senha() != null) {
            existente.setSenha(passwordEncoder.encode(dto.senha()));
        }

        if (dto.email() != null) {
            existente.setEmail(dto.email());
        }

        return new CredencialDTO(credencialRepository.save(existente));
    }

    @Override
    public void delete(Long id) {
        credencialRepository.deleteById(id);
    }
}
