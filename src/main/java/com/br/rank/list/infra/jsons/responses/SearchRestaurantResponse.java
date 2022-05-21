package com.br.rank.list.infra.jsons.responses;

import com.br.rank.list.domains.SearchRestaurant;

public record SearchRestaurantResponse(
        String id,
        String name
) {

    public static SearchRestaurantResponse from(final SearchRestaurant searchRestaurant) {
        return new SearchRestaurantResponse(searchRestaurant.getId(), searchRestaurant.getName());
    }
}
