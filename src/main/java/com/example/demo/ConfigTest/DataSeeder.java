package com.example.demo.ConfigTest;

import com.example.demo.DTO.Credencial.CredencialCreateDTO;
import com.example.demo.DTO.Usuario.UsuarioCreateDTO;
import com.example.demo.Model.Credencial;
import com.example.demo.Model.Usuario;
import com.example.demo.Repository.CredencialRepository;
import com.example.demo.Repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;

@Configuration
public class DataSeeder {
    @Bean
    @Order(1)
    CommandLineRunner seedUsuarios(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                UsuarioCreateDTO usuarioDTO = new UsuarioCreateDTO("Richard", "Alves", "21123456789", LocalDate.of(2006, 7, 27));
                Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
                usuarioRepository.save(usuario);

                usuarioDTO = new UsuarioCreateDTO("Taylan", "Gonzaga", "21123456789", LocalDate.of(2006, 4, 2));
                usuario = modelMapper.map(usuarioDTO, Usuario.class);
                usuarioRepository.save(usuario);

                usuarioDTO = new UsuarioCreateDTO("Juan", "Santos", "21123456789", LocalDate.of(2006, 1, 29));
                usuario = modelMapper.map(usuarioDTO, Usuario.class);
                usuarioRepository.save(usuario);

                usuarioDTO = new UsuarioCreateDTO("Guilherme", "Pirozi", "21123456789", LocalDate.of(2005, 6, 22));
                usuario = modelMapper.map(usuarioDTO, Usuario.class);
                usuarioRepository.save(usuario);

                usuarioDTO = new UsuarioCreateDTO("Lucas", "Pereira", "21123456789", LocalDate.of(2001, 5, 26));
                usuario = modelMapper.map(usuarioDTO, Usuario.class);
                usuarioRepository.save(usuario);
            }
        };
    }
    @Bean
    @Order(2)
    CommandLineRunner seedCredencial(CredencialRepository credencialRepository, ModelMapper modelMapper) {
        return args -> {
            if (credencialRepository.count() == 0) {
                CredencialCreateDTO credencialDTO = new CredencialCreateDTO("richard.alves@gmail.com", "1234567890");
                Credencial credencial = modelMapper.map(credencialDTO, Credencial.class);
                credencialRepository.save(credencial);

                credencialDTO = new CredencialCreateDTO("taylan.gonzaga@gmail.com", "1234567890");
                credencial = modelMapper.map(credencialDTO, Credencial.class);
                credencialRepository.save(credencial);

                credencialDTO = new CredencialCreateDTO("juan.santos@gmail.com", "1234567890");
                credencial = modelMapper.map(credencialDTO, Credencial.class);
                credencialRepository.save(credencial);

                credencialDTO = new CredencialCreateDTO("guilherme.pirozi@gmail.com", "1234567890");
                credencial = modelMapper.map(credencialDTO, Credencial.class);
                credencialRepository.save(credencial);

                credencialDTO = new CredencialCreateDTO("lucas.pereira@gmail.com", "1234567890");
                credencial = modelMapper.map(credencialDTO, Credencial.class);
                credencialRepository.save(credencial);
            }
        };
    }
}
