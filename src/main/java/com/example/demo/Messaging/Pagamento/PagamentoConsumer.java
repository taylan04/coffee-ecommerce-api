package com.example.demo.Messaging.Pagamento;

import com.example.demo.DTO.Pedido.PedidoDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoConsumer {
    @RabbitListener(queues = "pagamento.fila")
    public void consumirPagamento(Object pagamento) {
        System.out.println("Pagamento recebido - " + pagamento.toString());
        //Enviar mensagem para transportadora para pedido efetuado com sucesso e com pagamento efetuado
    }
}
