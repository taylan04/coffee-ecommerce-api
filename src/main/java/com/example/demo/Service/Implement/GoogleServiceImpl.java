package com.example.demo.Service.Implement;

import com.example.demo.DTO.Auth.LoginGoogleDTO;
import com.example.demo.DTO.Auth.LoginRespostaDTO;
import com.example.demo.DTO.Credencial.CredencialDTO;
import com.example.demo.Model.AuthProvedor;
import com.example.demo.Model.Credencial;
import com.example.demo.DTO.Auth.GoogleUserData;
import com.example.demo.Model.Usuario;
import com.example.demo.Repository.CredencialRepository;
import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.Service.GoogleService;
import com.example.demo.Service.TokenService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class GoogleServiceImpl implements GoogleService {

    private final UsuarioRepository usuarioRepository;
    private final CredencialRepository credencialRepository;
    private final TokenService tokenService;

    public GoogleServiceImpl(UsuarioRepository usuarioRepository, CredencialRepository credencialRepository, GoogleService googleService, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.credencialRepository = credencialRepository;
        this.googleService = googleService;
        this.tokenService = tokenService;
    }

    @Override
    public GoogleUserData validarToken(String token) {

        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(),
                    new GsonFactory() //biblioteca que lê json
            )
                    .setAudience(Collections.singletonList("SEU_CLIENT_ID_GOOGLE")) //verifica de se ta dentro do meu "grupo"
                    .build(); // pronto para entregar as info

            GoogleIdToken idToken = verifier.verify(token);

            if (idToken == null) {
                throw new RuntimeException("Token Google inválido");
            }

            GoogleIdToken.Payload payload = idToken.getPayload();

            String email = payload.getEmail();
            String nome = (String) payload.get("given_name");
            String sobrenome = (String) payload.get("family_name");

            return new GoogleUserData(email, nome, sobrenome);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao validar token do Google", e);
        }
    }

    @Transactional
    @Override
    public Credencial criarUsuarioGoogle(GoogleUserData data) {

        Usuario usuario = new Usuario();
        usuario.setNome(data.nome());
        usuario.setSobrenome(data.sobrenome());

        Credencial credencial = new Credencial();
        credencial.setEmail(data.email());
        credencial.setUsuario(usuario);
        credencial.setProvedor(AuthProvedor.GOOGLE);
        // senha fake (ou aleatória segura)
        credencial.setSenha(UUID.randomUUID().toString());

        usuarioRepository.save(usuario);
        return credencialRepository.save(credencial);
    }

    @Override
    public LoginRespostaDTO logarComGoogle(LoginGoogleDTO dto) {
        GoogleUserData data = validarToken(dto.token());
        Credencial credencial = credencialRepository.findByEmail(data.email())
                .orElseGet(() -> criarUsuarioGoogle(data));

        String token = tokenService.gerarToken(credencial);

        return new LoginRespostaDTO(true,
                "Login efetuado com sucesso!",
                new CredencialDTO(credencial),
                token);
    }
}
