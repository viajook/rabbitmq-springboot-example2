package via.sdj3.rabbitmqspringbootexample2.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import via.sdj3.rabbitmqspringbootexample2.model.Product;

@Service
public class ProductSubscriber {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductSubscriber.class);

    @RabbitListener(queues = "products.queue")
    public void receiveProduct(Product product) {
        LOGGER.info(String.format("<<<< Received from RabbitMQ Broker:  %s", product.toString()));
        System.out.println("\nFinished processing ===>>> \nDetails \n"
                + "Product:" + product.getDescription() + "\nWeight:" + product.getNumberOfParts()
                + "\nDate packed:" + product.getPacked() );
        // to save to a database, implement a repository
        // productRepository.save(product)
    }
}
