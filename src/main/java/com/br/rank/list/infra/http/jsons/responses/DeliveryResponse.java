package com.br.rank.list.infra.http.jsons.responses;

import com.br.rank.list.domains.Delivery;

import java.math.BigDecimal;

public record DeliveryResponse(
        int minimumDeliveryTime,
        int maximumDeliveryTime,
        BigDecimal rate
){
    public static DeliveryResponse from(final Delivery delivery) {
        return new DeliveryResponse(delivery.getMinimumDeliveryTime(), delivery.getMaximumDeliveryTime(), delivery.getRate());
    }
}
