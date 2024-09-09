package br.com.tavuencas.sergio.desafio_btg_pactual_orderms.controller;

import br.com.tavuencas.sergio.desafio_btg_pactual_orderms.entity.Order;

import java.math.BigDecimal;

public record OrderResponse(Long orderId,
                            Long customerId,
                            BigDecimal total) {

    public static OrderResponse fromEntity(Order entity) {
        return new OrderResponse(entity.getId(), entity.getCustomerId(), entity.getTotal());
    }
}
