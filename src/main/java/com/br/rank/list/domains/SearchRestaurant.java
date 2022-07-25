package com.br.rank.list.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public final class SearchRestaurant {

    private String id;
    private String name;

    private SearchRestaurant(final Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
    }

    private SearchRestaurant(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static SearchRestaurant from(final Restaurant restaurant) {
        return new SearchRestaurant(restaurant);
    }

    public static SearchRestaurant of(final String id, final String name) {
        return new SearchRestaurant(id, name);
    }
}
