package com.example.demo.Repository;

import com.example.demo.Model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    Optional<Pagamento> findByPedidoIdPedido(Long idPedido);
}

