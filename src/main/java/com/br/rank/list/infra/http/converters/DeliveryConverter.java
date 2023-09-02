package com.br.rank.list.infra.http.converters;

import com.br.rank.list.domains.Delivery;
import com.br.rank.list.infra.http.jsons.requests.DeliveryRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryConverter {

    public static Delivery toDomain(final DeliveryRequest request) {
        return Delivery.of(request.minimumDeliveryTime(), request.maximumDeliveryTime(), request.rate());
    }
}
