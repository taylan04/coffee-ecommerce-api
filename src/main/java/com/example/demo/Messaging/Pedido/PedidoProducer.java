package com.example.demo.Messaging.Pedido;

import com.example.demo.DTO.Pedido.PedidoDTO;
import com.example.demo.Messaging.MessageProducer;
import org.springframework.stereotype.Component;

@Component
public class PedidoProducer {
    public static final String QUEUE_PEDIDO = "pedido.fila";
    private final MessageProducer messageProducer;

    public PedidoProducer(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public void enviarMensagemPedido(PedidoDTO message) {
        System.out.print("Pedido Criado - " + message.toString());
        messageProducer.enviarMensagem(QUEUE_PEDIDO, message);
    }
}
