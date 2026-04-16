package com.example.demo.Messaging;

public interface MessageProducer {
    void enviarMensagem(String fila, Object message);
}
