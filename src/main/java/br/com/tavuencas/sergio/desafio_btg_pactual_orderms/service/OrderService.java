package br.com.tavuencas.sergio.desafio_btg_pactual_orderms.service;

import br.com.tavuencas.sergio.desafio_btg_pactual_orderms.controller.OrderResponse;
import br.com.tavuencas.sergio.desafio_btg_pactual_orderms.entity.Order;
import br.com.tavuencas.sergio.desafio_btg_pactual_orderms.entity.OrderItem;
import br.com.tavuencas.sergio.desafio_btg_pactual_orderms.listener.dto.OrderCreatedEvent;
import br.com.tavuencas.sergio.desafio_btg_pactual_orderms.repository.OrderRepository;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final MongoTemplate template;

    public OrderService(OrderRepository repository, MongoTemplate template) {
        this.repository = repository;
        this.template = template;
    }

    public void save(OrderCreatedEvent event) {
        var entity = new Order();

        entity.setId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());
        entity.setTotal(getTotal(event));
        entity.setItems(getOrderItems(event));

        repository.save(entity);
    }

    public BigDecimal findTotalOnOrdersByCustomerId(Long customerId) {
        var aggregations = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                group().sum("total").as("total")
        );

        var response = template.aggregate(aggregations, "orders", Document.class);

        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest) {
        var orders = repository.findAllByCustomerId(customerId, pageRequest);

        return orders.map(OrderResponse::fromEntity);
    }

    private static BigDecimal getTotal(OrderCreatedEvent event) {
        return event.itens()
                .stream()
                .map(item -> item.preco().multiply(BigDecimal.valueOf(item.quantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private static List<OrderItem> getOrderItems(OrderCreatedEvent event) {
        return event.itens().stream()
                .map(item -> new OrderItem(item.produto(), item.quantidade(), item.preco()))
                .toList();
    }
}
