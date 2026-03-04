package com.example.demo.Service;

import com.example.demo.DTO.UsuarioCreateDTO;
import com.example.demo.DTO.UsuarioDTO;
import com.example.demo.DTO.UsuarioUpdateDTO;
import com.example.demo.Model.Usuario;

import java.math.BigInteger;
import java.util.List;

public interface UsuarioService {
    UsuarioDTO findById(Long id);
    UsuarioDTO save(UsuarioCreateDTO dto);
    List<UsuarioDTO> findAll();
    UsuarioDTO update(Long id, UsuarioUpdateDTO dto);
    public void delete(Long id);
}
