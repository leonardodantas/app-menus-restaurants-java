package com.br.rank.list.infra.jsons.responses;

import com.br.rank.list.domains.Restaurant;

import java.time.LocalTime;

public record RestaurantResponse(
        String id,
        String code,
        String name,
        AddressResponse address,
        LocalTime startTime,
        LocalTime endTime
) {
    public static RestaurantResponse from(final Restaurant restaurant) {
        return new RestaurantResponse(restaurant.getId(), restaurant.getCode(), restaurant.getName(), AddressResponse.from(restaurant.getAddress()), restaurant.getStartTime(), restaurant.getEndTime());
    }
}
