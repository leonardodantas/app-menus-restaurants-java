package com.br.rank.list.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SearchRestaurant {

    private String id;
    private String name;

    private SearchRestaurant(final Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
    }

    public static SearchRestaurant from(final Restaurant restaurant) {
        return new SearchRestaurant(restaurant);
    }
}
