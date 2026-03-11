package com.example.demo.Service;

import com.example.demo.DTO.Credencial.CredencialCreateDTO;
import com.example.demo.DTO.Credencial.CredencialDTO;
import com.example.demo.DTO.Credencial.CredencialUpdateDTO;
import com.example.demo.DTO.Login.LoginRequestDTO;
import com.example.demo.DTO.Login.LoginRespostaDTO;
import com.example.demo.Model.Credencial;

import java.util.List;

public interface CredencialService {
    //todos retornam CredencialDTO porque é o dto de resposta
    public CredencialDTO findById(Long id);
    public List<CredencialDTO> findAll();
    public CredencialDTO save(CredencialCreateDTO credencial);
    public CredencialDTO update(Long id, CredencialUpdateDTO credencial);
    public LoginRespostaDTO verificarAutenticidade(LoginRequestDTO login);
    public void delete(Long id);
}
