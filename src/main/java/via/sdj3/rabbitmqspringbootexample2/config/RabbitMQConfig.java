package via.sdj3.rabbitmqspringbootexample2.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // rabbitmq direct exchange spring bean
    @Bean
    public Exchange productExchange() {
        return new DirectExchange("products.exchange");
    }

    @Bean
    public Queue productQueue(){
        return new Queue("products.queue");  // queue 1
    }

    @Bean
    public Queue messageQueue() {
        return new Queue("distribution.notifications");  // queue 2
    }

    // binding between the queue and exchange using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(productQueue())
                .to(productExchange())
                .with("products.routingKey").noargs();
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(messageQueue())
                .to(productExchange())
                .with("distribution.notifications.routingKey").noargs();
    }

    // configure a converter for JSON for serialization and deserialization
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}