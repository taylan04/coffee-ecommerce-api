package com.example.demo.Controller;


import com.example.demo.DTO.Auth.LoginGoogleDTO;
import com.example.demo.DTO.Auth.LoginRequestDTO;
import com.example.demo.DTO.Auth.LoginRespostaDTO;
import com.example.demo.DTO.Auth.RegistroRequestDTO;
import com.example.demo.Model.Credencial;
import com.example.demo.DTO.Auth.GoogleUserData;
import com.example.demo.Repository.CredencialRepository;
import com.example.demo.Service.AuthService;
import com.example.demo.Service.GoogleService;
import com.example.demo.Service.TokenService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticacao")
public class AuthController {

    private final AuthService authService;
    private final GoogleService googleService;

    public AuthController(AuthService authService, CredencialRepository credencialRepository, GoogleService googleService, TokenService tokenService) {
        this.authService = authService;
        this.credencialRepository = credencialRepository;
        this.googleService = googleService;
        this.tokenService = tokenService;
    }

    //@Valid funciona para o controller validar as anotações colocadas nos DTOs
    @PostMapping("/registro")
    public LoginRespostaDTO registro(@RequestBody @Valid RegistroRequestDTO registroRequestDTO) {
        return authService.registrar(registroRequestDTO);
    }

    @PostMapping("/login")
    public LoginRespostaDTO login(@RequestBody @Valid LoginRequestDTO dto) {
        return authService.verificarAutenticidade(dto);
    }

    @PostMapping("/google")
    public LoginRespostaDTO loginGoogle(@RequestBody LoginGoogleDTO dto) {
        return googleService.logarComGoogle(dto);
    }

    /* decidir se isso vai ficar assim
    @GetMapping("/eu")
    public UsuarioDTO eu(Authentication authentication) {
        Credencial credencial = (Credencial) authentication.getPrincipal();
        return new UsuarioDTO(credencial.getUsuario());
    }*/
}
