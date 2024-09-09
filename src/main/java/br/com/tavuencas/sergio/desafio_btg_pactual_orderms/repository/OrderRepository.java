package br.com.tavuencas.sergio.desafio_btg_pactual_orderms.repository;

import br.com.tavuencas.sergio.desafio_btg_pactual_orderms.controller.OrderResponse;
import br.com.tavuencas.sergio.desafio_btg_pactual_orderms.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {

    Page<Order> findAllByCustomerId(Long customerId, PageRequest pageRequest);
}
