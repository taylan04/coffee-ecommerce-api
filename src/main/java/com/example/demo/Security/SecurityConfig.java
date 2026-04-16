package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Essa classe é uma classe de configuração do Spring.
@Configuration
public class SecurityConfig {

    //@Bean Diz que o objeto retornado será gerenciado pelo Spring
    @Bean
    public PasswordEncoder senhaCriptografada() {
        return new BCryptPasswordEncoder();
    }

    //Peguei esse método de um artigo para desativar o Security By Default que vem por padão
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //desativa proteção dos endpoints
        http.csrf(csrf -> csrf.disable()) // desativa proteção CSRF (usado em APIs)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // permite qualquer requisição
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // desativa login basic
                .formLogin(form -> form.disable()); // desativa tela de login


        //ativa proteção dos endpoints exceto de autenticação
        /*http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/autenticacao/**").permitAll()
                        .anyRequest().authenticated()
                );*/


        return http.build();
    }
}
