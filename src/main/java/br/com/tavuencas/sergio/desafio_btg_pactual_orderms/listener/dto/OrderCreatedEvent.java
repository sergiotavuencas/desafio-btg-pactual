package br.com.tavuencas.sergio.desafio_btg_pactual_orderms.listener.dto;

import java.util.List;

public record OrderCreatedEvent(
        Long codigoPedido,
        Long codigoCliente,
        List<OrderItemEvent> itens
) {
}
