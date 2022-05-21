package com.br.rank.list.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document("searchRestaurant")
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
