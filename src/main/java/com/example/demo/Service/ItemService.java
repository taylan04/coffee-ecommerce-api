package com.example.demo.Service;

import com.example.demo.DTO.Item.ItemCreateDTO;
import com.example.demo.DTO.Item.ItemDTO;
import com.example.demo.DTO.Item.ItemUpdateDTO;
import java.util.List;

public interface ItemService {
    public ItemDTO findById(Long id);
    public List<ItemDTO> findAll();
    public ItemDTO save(ItemCreateDTO item);
    public ItemDTO update(Long id, ItemUpdateDTO item);
    public void delete(Long id);
    public List<ItemDTO> buscarPorPedido(Long id);
}
