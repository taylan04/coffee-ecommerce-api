package com.example.demo.Messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitProducer implements MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void enviarMensagem(String fila, Object mensagem) {
        rabbitTemplate.convertAndSend(fila, mensagem);
    }
}
