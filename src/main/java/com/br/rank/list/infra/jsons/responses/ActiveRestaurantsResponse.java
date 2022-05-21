package com.br.rank.list.infra.jsons.responses;

import com.br.rank.list.domains.OperatingHours;
import com.br.rank.list.domains.Restaurant;

public record ActiveRestaurantsResponse(
        String id,
        String name,
        OperatingHours openingHours,
        boolean deliveryAvailable,
        boolean open
) {

    public static ActiveRestaurantsResponse from(final Restaurant restaurant) {
        return new ActiveRestaurantsResponse(restaurant.getId(), restaurant.getName(), restaurant.getOpeningHours(), restaurant.isDeliveryAvailable(), restaurant.isOpen());
    }
}
