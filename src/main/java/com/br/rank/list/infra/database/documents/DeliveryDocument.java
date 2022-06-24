package com.br.rank.list.infra.database.documents;

import com.br.rank.list.domains.Delivery;

import java.math.BigDecimal;

public record DeliveryDocument(
        int minimumDeliveryTime,
        int maximumDeliveryTime,
        BigDecimal rate
) {

    public static DeliveryDocument from(final Delivery delivery) {
        return new DeliveryDocument(delivery.getMinimumDeliveryTime(), delivery.getMaximumDeliveryTime(), delivery.getRate());
    }
}
