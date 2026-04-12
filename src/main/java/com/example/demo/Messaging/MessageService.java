package com.example.demo.Messaging;

public interface MessageService {
    void enviarMensagem(String mensagem);
    String receberMensagem();
    void pedidoEnviarMensagem();
    String pedidoReceberMensagem();
    void pedidoExpressoEnviarMensagem();
    String pedidoExpressoReceberMensagem();
}
