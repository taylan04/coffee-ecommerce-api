package com.example.demo.Service;

import com.example.demo.DTO.*;
import com.example.demo.Model.Credencial;

import java.util.List;

public interface CredencialService {
    //todos retornam CredencialDTO porque é o dto de resposta
    public CredencialDTO findById(Long id);
    public List<CredencialDTO> findAll();
    public CredencialDTO save(CredencialCreateDTO credencial);
    public CredencialDTO update(Long id, CredencialUpdateDTO credencial);
    public LoginRespostaDTO verificarAutenticidade(Credencial credencial);
    public void delete(Long id);
}
