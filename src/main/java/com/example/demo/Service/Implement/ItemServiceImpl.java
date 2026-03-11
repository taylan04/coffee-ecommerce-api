package com.example.demo.Service.Implement;

import com.example.demo.DTO.Endereco.EnderecoDTO;
import com.example.demo.DTO.Item.ItemCreateDTO;
import com.example.demo.DTO.Item.ItemDTO;
import com.example.demo.DTO.Item.ItemUpdateDTO;
import com.example.demo.Model.*;
import com.example.demo.Repository.ItemRepository;
import com.example.demo.Repository.PedidoRepository;
import com.example.demo.Repository.ProdutoRepository;
import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.Service.ItemService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    public ItemServiceImpl(ItemRepository itemRepository, ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.itemRepository = itemRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public ItemDTO findById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado."));

        return new ItemDTO(item);
    }

    @Override
    public List<ItemDTO> findAll() {
        List<Item> itens = itemRepository.findAll();
        List<ItemDTO> itemDTOS = new ArrayList<>();

        for (Item item : itens) {
            itemDTOS.add(new ItemDTO(item));
        }

        return itemDTOS;
    }

    @Override
    public ItemDTO save(ItemCreateDTO dto) {
        Produto produto = produtoRepository.findById(dto.idProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        Pedido pedido = pedidoRepository.findById(dto.idPedido())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

        Item item = new Item(dto, produto, pedido);
        return new ItemDTO(itemRepository.save(item));
    }

    @Override
    public ItemDTO update(Long id, ItemUpdateDTO novo) {
        Item existente = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrada."));

        if (novo.quantidade() != null) {
            existente.setQuantidade(novo.quantidade());
        }

        if (novo.subtotal() != null) {
            existente.setSubtotal(novo.subtotal());
        }

        //melhor retornar o objeto ou null?
        return new ItemDTO(itemRepository.save(existente));
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<ItemDTO> buscarPorPedido(Long idPedido) {
        List<Item> itens = itemRepository.findByIdPedido(idPedido);
        List<ItemDTO> itensDTOS = new ArrayList<>();
        for (Item item : itens) {
            itensDTOS.add(new ItemDTO(item));
        }
        return itensDTOS;
    }
}
