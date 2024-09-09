package br.com.tavuencas.sergio.desafio_btg_pactual_orderms.listener;

import br.com.tavuencas.sergio.desafio_btg_pactual_orderms.listener.dto.OrderCreatedEvent;
import br.com.tavuencas.sergio.desafio_btg_pactual_orderms.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static br.com.tavuencas.sergio.desafio_btg_pactual_orderms.config.RabbitMqConfig.ORDER_CREATE_QUEUE;

@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderService service;

    public OrderCreatedListener(OrderService service) {
        this.service = service;
    }

    @RabbitListener(queues = ORDER_CREATE_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {
        logger.info("Message consumed: {}", message);

        service.save(message.getPayload());
    }
}
