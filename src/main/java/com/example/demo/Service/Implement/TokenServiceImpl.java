package com.example.demo.Service.Implement;

import com.example.demo.Model.Credencial;
import com.example.demo.Service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.secret}") // usa a senha que está na .env e especificada em application.properties
    private String SECRET_KEY;

    // Token válido por 1 hora
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    // Gera o token JWT a partir da credencial
    //claims = Informações (dados) que você coloca dentro do token.
    public String gerarToken(Credencial credencial) {
        return Jwts.builder()
                .setSubject(credencial.getEmail()) // identifica o usuário
                .claim("usuario", Map.of(
                        "nome", credencial.getUsuario().getNome(),
                        "sobrenome", credencial.getUsuario().getSobrenome()
                )) // nome e sobrenome do usuario
                .claim("roles", credencial.getRoles())
                .setIssuedAt(new Date()) // data de emissão do token
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // // expiração do emissão do token
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Verifica se o token é válido (assinatura correta e não expirado)
    // Usado antes de permitir acesso a endpoints protegidos
    public boolean validarToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extrai o email do token
    // Usado para identificar qual usuário está fazendo a requisição
    public String extrairEmail(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}