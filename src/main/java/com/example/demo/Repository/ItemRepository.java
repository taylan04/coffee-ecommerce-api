package com.example.demo.Repository;

import com.example.demo.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByIdPedido(Long pedidoId);
}
