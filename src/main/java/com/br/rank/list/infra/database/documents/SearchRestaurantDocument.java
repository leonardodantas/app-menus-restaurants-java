package com.br.rank.list.infra.database.documents;

import com.br.rank.list.domains.SearchRestaurant;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("searchRestaurant")
public record SearchRestaurantDocument(
        String id,
        String name

) {

    public static SearchRestaurantDocument from(final SearchRestaurant searchRestaurant) {
        return new SearchRestaurantDocument(searchRestaurant.getId(), searchRestaurant.getName());
    }
}
