package com.example.demo.Service.Implement;

import com.example.demo.DTO.Endereco.EnderecoDTO;
import com.example.demo.DTO.Pedido.PedidoCreateDTO;
import com.example.demo.DTO.Pedido.PedidoDTO;
import com.example.demo.DTO.Pedido.PedidoUpdateDTO;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Cupom;
import com.example.demo.Model.Endereco;
import com.example.demo.Model.Pedido;
import com.example.demo.Model.Usuario;
import com.example.demo.Repository.CupomRepository;
import com.example.demo.Repository.PedidoRepository;
import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.Service.PedidoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CupomRepository cupomRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, CupomRepository cupomRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cupomRepository = cupomRepository;
    }

    @Override
    public PedidoDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));

        return new PedidoDTO(pedido);
    }

    @Override
    public List<PedidoDTO> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> pedidoDTOS = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            pedidoDTOS.add(new PedidoDTO(pedido));
        }

        return pedidoDTOS;
    }

    @Override
    public PedidoDTO save(PedidoCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Cupom cupom = null;
        if (dto.idCupom() != null) {
            cupom = cupomRepository.findById(dto.idCupom())
                    .orElseThrow(() -> new ResourceNotFoundException("Cupom não encontrado"));
        }

        Pedido pedido = new Pedido(dto, cupom, usuario);
        return new PedidoDTO(pedidoRepository.save(pedido));
    }

    @Override
    public PedidoDTO update(Long id, PedidoUpdateDTO novo) {
        Pedido existente = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));

        if (novo.estado() != null) {
            existente.setEstado(novo.estado());
        }

        if (novo.desconto() != null) {
            existente.setDesconto(novo.desconto());
        }

        if (novo.valorTotal() != null) {
            existente.setValorTotal(novo.valorTotal());
        }

        if (novo.valorFinal() != null) {
            existente.setValorFinal(novo.valorFinal());
        }

        return new PedidoDTO(pedidoRepository.save(existente));
    }

    @Override
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public List<PedidoDTO> buscarPorUsuario(Long idUsuario) {
        List<Pedido> pedidos = pedidoRepository.findByUsuarioIdUsuario(idUsuario);
        List<PedidoDTO> pedidosDTOS = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            pedidosDTOS.add(new PedidoDTO(pedido));
        }
        return pedidosDTOS;
    }
}
