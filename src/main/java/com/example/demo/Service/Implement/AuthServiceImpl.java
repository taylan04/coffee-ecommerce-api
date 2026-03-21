package com.example.demo.Service.Implement;

import com.example.demo.DTO.Auth.LoginRequestDTO;
import com.example.demo.DTO.Auth.LoginRespostaDTO;
import com.example.demo.DTO.Auth.RegistroRequestDTO;
import com.example.demo.DTO.Credencial.CredencialDTO;
import com.example.demo.Exception.AlreadyExistsExcpetion;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.AuthProvedor;
import com.example.demo.Model.Credencial;
import com.example.demo.Model.Role;
import com.example.demo.Model.Usuario;
import com.example.demo.Repository.CredencialRepository;
import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.Service.AuthService;
import com.example.demo.Service.TokenService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final CredencialRepository credencialRepository;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    ModelMapper modelMapper = new ModelMapper();

    public AuthServiceImpl(CredencialRepository credencialRepository, UsuarioRepository usuarioRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.credencialRepository = credencialRepository;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public LoginRespostaDTO registrar(RegistroRequestDTO registro) {

        //Transactional = Execute tudo dentro de uma transação. Se algo der erro, desfaz tudo
        //esse método isPresent verifica se valores optional retornam algum valor ou null
        if (credencialRepository.findByEmail(registro.email()).isPresent()) {
            throw new AlreadyExistsExcpetion("Email já cadastrado");
        }

        Usuario usuario = modelMapper.map(registro, Usuario.class);
        Credencial credencial = modelMapper.map(registro, Credencial.class);
        credencial.setSenha(passwordEncoder.encode(registro.senha()));

        credencial.setUsuario(usuario);
        Credencial credencialSalva = credencialRepository.save(credencial);

        String token = tokenService.gerarToken(credencialSalva);

        return new LoginRespostaDTO(true,
                "Login efetuado com sucesso!",
                new CredencialDTO(credencialSalva),
                token);
    }


    @Override
    public LoginRespostaDTO verificarAutenticidade(LoginRequestDTO login) {

        Optional<Credencial> existente = credencialRepository.findByEmail(login.email());

        if (existente.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }

        Credencial credencialExistente = existente.get();

        if (credencialExistente.getProvedor() == AuthProvedor.GOOGLE) {
            return new LoginRespostaDTO(false,
                    "Use 'Entrar com Google' para acessar esta conta.",
                    null,
                    null);
        }

        if (!passwordEncoder.matches(login.senha(), credencialExistente.getSenha())) {
            return new LoginRespostaDTO(false, "Senha inválida", null, null);
        }

        String token = tokenService.gerarToken(credencialExistente);

        return new LoginRespostaDTO(true,
                "Login efetuado com sucesso!",
                new CredencialDTO(credencialExistente),
                token);
    }
}
