package com.example.demo.Service;

import com.example.demo.DTO.EnderecoCreateDTO;
import com.example.demo.DTO.EnderecoDTO;
import com.example.demo.DTO.EnderecoUpdateDTO;
import com.example.demo.Model.Credencial;
import com.example.demo.Model.Endereco;

import java.math.BigInteger;
import java.util.List;

public interface EnderecoService {
    public EnderecoDTO findById(Long id);
    public List<EnderecoDTO> findAll();
    public EnderecoDTO save(EnderecoCreateDTO endereco);
    public EnderecoDTO update(Long id, EnderecoUpdateDTO endereco);
    public void delete(Long id);
    public EnderecoDTO buscarPorUsuario(Long id);
}
