package com.example.demo.Service;

import com.example.demo.DTO.Endereco.EnderecoCreateDTO;
import com.example.demo.DTO.Endereco.EnderecoDTO;
import com.example.demo.DTO.Endereco.EnderecoUpdateDTO;

import java.util.List;

public interface EnderecoService {
    public EnderecoDTO findById(Long id);
    public List<EnderecoDTO> findAll();
    public EnderecoDTO save(EnderecoCreateDTO endereco);
    public EnderecoDTO update(Long id, EnderecoUpdateDTO endereco);
    public void delete(Long id);
    public List<EnderecoDTO> buscarPorUsuario(Long id);}
