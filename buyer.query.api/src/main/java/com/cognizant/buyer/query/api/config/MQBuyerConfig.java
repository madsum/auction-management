package com.cognizant.buyer.query.api.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQBuyerConfig {

    public static final String QUEUE = "auction_queue";
    public static final String EXCHANGE = "auction_exchange";
    public static final String ROUTING_KEY = "auction_routingKey";

    @Bean(name = "auction_queue")
    public Queue queue() {
        return  new Queue(QUEUE);
    }

    @Bean(name = "auction_exchange")
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean(name = "auction_binding")
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    @Bean(name = "auction_messageConverter")
    public MessageConverter messageConverter() {
        return  new Jackson2JsonMessageConverter();
    }

    @Bean(name = "auction_template")
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return  template;
    }

}
