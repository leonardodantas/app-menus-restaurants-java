package com.br.rank.list.infra.converters;

import com.br.rank.list.domains.Delivery;
import com.br.rank.list.infra.jsons.requests.DeliveryRequest;

public class DeliveryConverter {

    public static Delivery toDomain(final DeliveryRequest request) {
        return Delivery.builder()
                .minimumDeliveryTime(request.minimumDeliveryTime())
                .maximumDeliveryTime(request.maximumDeliveryTime())
                .rate(request.rate())
                .build();
    }
}
