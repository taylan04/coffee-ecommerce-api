package com.example.demo.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CEP {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade; // cidade
    private String uf;
    private String ddd;
    private String complemento;
    private Boolean erro;
}
