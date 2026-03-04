package com.example.demo.DTO;

public record EnderecoUpdateDTO (
    String logradouro,
    Integer numero,
    String complemento,
    String referencia,
    String bairro,
    String cep,
    String cidade,
    String estado,
    String pais
) {}
