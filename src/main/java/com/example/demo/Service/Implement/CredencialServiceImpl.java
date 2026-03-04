package com.example.demo.Service.Implement;

import com.example.demo.DTO.CredencialCreateDTO;
import com.example.demo.DTO.CredencialDTO;
import com.example.demo.DTO.CredencialUpdateDTO;
import com.example.demo.DTO.LoginRespostaDTO;
import com.example.demo.Model.Credencial;
import com.example.demo.Repository.CredencialRepository;
import com.example.demo.Service.CredencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CredencialServiceImpl implements CredencialService {

    private final CredencialRepository credencialRepository;

    //Esse autowired é porque essa classe é de config e tem o @bean, ou seja, ela é gerenciada pelo Spring
    //framework, e eu preciso dessa anotação para pedir ao spring que injete ele
    @Autowired
    private PasswordEncoder passwordEncoder;

    private JwtServiceImpl jwtService;

    public CredencialServiceImpl(CredencialRepository credencialRepository) {
        this.credencialRepository = credencialRepository;
    }

    //Esses @Override em cima dos métodos é pra dizer olha, estou usando esse método que está na interface

    // Uso findById porque getById pode retornar um proxy (objeto temporário
    // que só carrega dados ao acessar seus atributos). findById é mais seguro.

    @Override
    public CredencialDTO findById(Long id) {
        Credencial credencial = credencialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credencial não encontrada."));
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

    //o service recebe em DTO e converte pra entidade, mas retorna em DTO novamente
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
                .orElseThrow(() -> new RuntimeException("Credencial não encontrada."));

        if (dto.senha() != null) {
            existente.setSenha(dto.senha());
        }

        if (dto.email() != null ) {
            existente.setEmail(dto.email());
        }

        //melhor retornar o objeto ou null?
        //Respondido: em padrão de mercado, não se deve retornar null. ( má prática )
        return new CredencialDTO(credencialRepository.save(existente));
    }

    @Override
    public void delete(Long id) {
        credencialRepository.deleteById(id);
    }

    @Override
    public LoginRespostaDTO verificarAutenticidade(Credencial credencial) {

        Optional<Credencial> existente = credencialRepository.findByEmail(credencial.getEmail());

        if (existente.isEmpty()) {
            return new LoginRespostaDTO(false, "Usuário não encontrado.", null, null);
        }

        //esse .get() pega o objeto credencial dentro do optional
        Credencial credencialExistente = existente.get();

        //esse matches compara a senha hash salva no banco com o hash da senha digitada
        if (!passwordEncoder.matches(credencial.getSenha(), credencialExistente.getSenha())) {
            return new LoginRespostaDTO(false, "Senha inválida", null, null);
        }

        String token = jwtService.gerarToken(credencialExistente);

        return new LoginRespostaDTO(true,
                "Login efetuado com sucesso!",
                new CredencialDTO(credencialExistente),
                token);
    }
}
