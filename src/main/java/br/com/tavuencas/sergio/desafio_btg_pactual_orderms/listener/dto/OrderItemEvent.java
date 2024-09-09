package br.com.tavuencas.sergio.desafio_btg_pactual_orderms.listener.dto;

import java.math.BigDecimal;

public record OrderItemEvent(
        String produto,
        Integer quantidade,
        BigDecimal preco
) {
}
