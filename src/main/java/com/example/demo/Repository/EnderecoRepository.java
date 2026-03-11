package com.example.demo.Repository;

import com.example.demo.Model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findByUsuarioIdUsuario(Long usuarioId);
}
