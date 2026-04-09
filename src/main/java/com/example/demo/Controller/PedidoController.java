package com.example.demo.Controller;

import com.example.demo.DTO.Item.ItemDTO;
import com.example.demo.DTO.Pedido.PedidoCreateDTO;
import com.example.demo.DTO.Pedido.PedidoDTO;
import com.example.demo.DTO.Pedido.PedidoUpdateDTO;
import com.example.demo.Service.ItemService;
import com.example.demo.Service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ItemService itemService;

    public PedidoController(PedidoService pedidoService, ItemService itemService) {
        this.pedidoService = pedidoService;
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public PedidoDTO findById(@PathVariable Long id) {
        return pedidoService.findById(id);
    }

    @GetMapping
    public List<PedidoDTO> findAll() {
        return pedidoService.findAll();
    }

    @PostMapping
    public PedidoDTO create(@RequestBody @Valid PedidoCreateDTO pedido) {
        return pedidoService.save(pedido);
    }

    @PatchMapping("/{id}")
    public PedidoDTO update(@PathVariable Long id, @RequestBody @Valid PedidoUpdateDTO pedido) {
        return pedidoService.update(id, pedido);
    }

    //implementar autenticação de delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pedidoService.delete(id);
    }

    @GetMapping("/{id}/itens")
    public List<ItemDTO> findAllItem(@PathVariable Long id) {
        return itemService.buscarPorPedido(id);
    }
}
