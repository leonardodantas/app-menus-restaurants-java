package com.br.rank.list.infra.database.converters;

import com.br.rank.list.domains.Categories;
import com.br.rank.list.domains.RestaurantCategories;
import com.br.rank.list.infra.database.documents.RestaurantCategoriesDocument;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantCategoriesConverter {

    public static RestaurantCategories toDomain(final RestaurantCategoriesDocument document) {
        return RestaurantCategories.of(document.id(),
                document.code(), Categories.from(document.values()));
    }
}
