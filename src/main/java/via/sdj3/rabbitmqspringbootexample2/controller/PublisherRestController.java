package via.sdj3.rabbitmqspringbootexample2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import via.sdj3.rabbitmqspringbootexample2.model.Product;
import via.sdj3.rabbitmqspringbootexample2.publisher.ProductPublisher;


@RestController
@RequestMapping("/api/v1")
public class PublisherRestController {
    private ProductPublisher rabbitMQService;

    public PublisherRestController(ProductPublisher rabbitMQService) {
        this.rabbitMQService = rabbitMQService;
    }

    @PostMapping("/products")
    public ResponseEntity<String> publishProductDetails(@RequestBody Product product){
        if(rabbitMQService.send(product)) {
            System.out.println(product.getDescription() + " sent to the RabbitMQ Broker" + HttpStatus.OK.toString());
            return ResponseEntity.ok(product.getDescription() + " sent to the RabbitMQ Broker");
        }
        else {
            return ResponseEntity.status(500).body("Error.....");
        }
    }
}