package com.example.demo.Messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }


    @Bean
    public Queue mensagemQueue() {
        System.out.println("Criação de Fila -> 'mensagem.fila'");
        return new Queue("mensagem.fila", true);
    }

    @Bean
    public Queue pedidoQueue() {
        System.out.println("Criação de Fila -> 'pedido.fila'");
        return new Queue("pedido.fila", true);
    }

    @Bean
    public Queue pagamentoQueue() {
        System.out.println("Criação de Fila -> 'pagamento.fila'");
        return new Queue("pagamento.fila", true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("app.exchange");
    }

    @Bean
    public Binding mensagemBinding() {
        return BindingBuilder.bind(mensagemQueue())
                .to(exchange())
                .with("mensagem.fila");
    }

    @Bean
    public Binding pedidoBinding() {
        return BindingBuilder.bind(pedidoQueue())
                .to(exchange())
                .with("pedido.fila");
    }

    @Bean
    public Binding pagamentoExpressoBinding() {
        return BindingBuilder.bind(pagamentoQueue())
                .to(exchange())
                .with("pagamento.fila");
    }
}