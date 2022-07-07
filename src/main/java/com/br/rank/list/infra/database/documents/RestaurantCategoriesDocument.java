package com.br.rank.list.infra.database.documents;

import com.br.rank.list.domains.RestaurantCategories;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document("restaurantCategories")
public record RestaurantCategoriesDocument(
        @Id
        String id,
        @Indexed(unique = true)
        String code,
        CategoriesDocument categories
) {


    public static RestaurantCategoriesDocument from(final RestaurantCategories restaurantCategories) {
        return new RestaurantCategoriesDocument(restaurantCategories.getId(), restaurantCategories.getCode(), CategoriesDocument.from(restaurantCategories.getCategories()));
    }

    public Collection<String> values() {
        return categories.values();
    }
}
