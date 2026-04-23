package com.example.demo.Repository.Implement;

import com.example.demo.Model.CEP;
import com.example.demo.Repository.CEPRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class ViaCEPRepository implements CEPRepository {
    private final  WebClient webClient;
    public ViaCEPRepository(WebClient webClient) {
        this.webClient = webClient;
    }
    private String retornarURLViaCEP(String cep) {
        return "https://viacep.com.br/ws/"+cep+"/json/";
    }
    @Override
    public CEP retornarCEP(String cep) {
        return webClient.get().uri(retornarURLViaCEP(cep)) .retrieve().bodyToMono(CEP.class).block();
    }
}

