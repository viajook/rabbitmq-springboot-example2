package via.sdj3.rabbitmqspringbootexample2.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import via.sdj3.rabbitmqspringbootexample2.model.Product;

@Service
public class ProductPublisher {
    private RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductPublisher.class);
    private String EXCHANGE = "products.exchange";
    private String ROUTING_KEY = "products.routingkey";

    public ProductPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public boolean send(Product product) {
        try {
            LOGGER.info(String.format("Message sent ===>  %s", product.toString()));
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, product);
            return true;
        } catch (Exception ex){
            LOGGER.error(String.format("Error >>>> %s", ex.toString()));
            return  false;
        }
    }
}