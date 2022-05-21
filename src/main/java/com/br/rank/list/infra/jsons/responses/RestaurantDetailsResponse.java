package com.br.rank.list.infra.jsons.responses;

import com.br.rank.list.domains.Restaurant;

import java.time.LocalTime;

public record RestaurantDetailsResponse(
        String id,
        String name,
        String cnpj,
        AddressResponse address,
        boolean deliveryAvailable,
        boolean open,
        DeliveryResponse delivery,
        LocalTime startTime,
        LocalTime endTime
) {
    public static RestaurantDetailsResponse from(final Restaurant restaurant) {
        return new RestaurantDetailsResponse(restaurant.getId(), restaurant.getName(), restaurant.getCnpj(),
                AddressResponse.from(restaurant.getAddress()), restaurant.isDeliveryAvailable(), restaurant.isOpen(),
                DeliveryResponse.from(restaurant.getDelivery()),restaurant.getStartTime(), restaurant.getEndTime());
    }
}
