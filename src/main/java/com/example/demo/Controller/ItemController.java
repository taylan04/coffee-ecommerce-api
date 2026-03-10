package com.example.demo.Controller;

import com.example.demo.DTO.Item.ItemCreateDTO;
import com.example.demo.DTO.Item.ItemDTO;
import com.example.demo.DTO.Item.ItemUpdateDTO;
import com.example.demo.Model.Endereco;
import com.example.demo.Model.Item;
import com.example.demo.Service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public ItemDTO findById(@PathVariable Long id) {
        return itemService.findById(id);
    }

    @GetMapping
    public List<ItemDTO> findAll() {
        return itemService.findAll();
    }

    @PostMapping
    public ItemDTO create(@RequestBody ItemCreateDTO item) {
        return itemService.save(item);
    }

    @PatchMapping("/{id}")
    public ItemDTO update(@PathVariable Long id, @RequestBody ItemUpdateDTO item) {
        return itemService.update(id, item);
    }

    //implementar autenticação de delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }

}
