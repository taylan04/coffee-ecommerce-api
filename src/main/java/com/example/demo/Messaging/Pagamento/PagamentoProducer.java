package com.example.demo.Messaging.Pagamento;

import com.example.demo.Messaging.MessageProducer;
import org.springframework.stereotype.Component;

@Component
public class PagamentoProducer {
    public static final String QUEUE_PAGAMENTO = "pagamento.fila";
    private final MessageProducer messageProducer;

    public PagamentoProducer(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public void enviarMensagemPagamento(Object message) {
        System.out.print("Pagamento - " + message.toString());
        messageProducer.enviarMensagem(QUEUE_PAGAMENTO, message);
    }
}
