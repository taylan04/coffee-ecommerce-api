package com.example.demo.Service;

import com.example.demo.DTO.Pedido.PedidoCreateDTO;
import com.example.demo.DTO.Pedido.PedidoDTO;
import com.example.demo.DTO.Pedido.PedidoUpdateDTO;
import com.example.demo.Model.Item;
import com.example.demo.Model.Pedido;

import java.math.BigInteger;
import java.util.List;

public interface PedidoService {
    public PedidoDTO findById(Long id);
    public List<PedidoDTO> findAll();
    public PedidoDTO save(PedidoCreateDTO pedido);
    public PedidoDTO update(Long id, PedidoUpdateDTO pedido);
    public void delete(Long id);
}
