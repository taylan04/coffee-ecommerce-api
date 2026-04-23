package com.example.demo.Repository.Implement;

import com.example.demo.Repository.CEPRepository;
import org.springframework.web.reactive.function.client.WebClient;

public class ViaCEPRepository implements CEPRepository {
    private WebClient webClient;
    public void ViaCEPRepository() {
        this.webClient = WebClient.create();
    }
    private String retornarURLViaCEP(String cep) {
        return "https://viacep.com.br/ws/"+cep+"/json/";
    }
    public String retornarCEP(String cep) {
        return webClient.get().uri("https://viacep.com.br/ws/" + cep + "/json/") .retrieve().toString();
    }
}

