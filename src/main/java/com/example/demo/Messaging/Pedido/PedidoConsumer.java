package com.example.demo.Messaging.Pedido;

import com.example.demo.DTO.Pedido.PedidoDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {
    @RabbitListener(queues = "pedido.fila")
    public void consumirPedido(PedidoDTO pedido) {
        System.out.println("Pedido recebido - " + pedido.toString());
        //Adicionar criação de pagamento ao receber pedido
    }
}
