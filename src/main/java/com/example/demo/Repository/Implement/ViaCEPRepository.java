package com.example.demo.Repository.Implement;

import com.example.demo.Model.CEP;
import com.example.demo.Repository.CEPRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class ViaCEPRepository implements CEPRepository {
    private WebClient webClient;
    public void ViaCEPRepository() {
        this.webClient = WebClient.create();
    }
    private String retornarURLViaCEP(String cep) {
        return "https://viacep.com.br/ws/"+cep+"/json/";
    }
    @Override
    public CEP retornarCEP(String cep) {
        return webClient.get().uri(retornarURLViaCEP(cep)) .retrieve().bodyToMono(CEP.class).block();
    }
}

