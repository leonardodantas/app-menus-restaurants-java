package com.br.rank.list.infra.database.converters;

import com.br.rank.list.domains.Categories;
import com.br.rank.list.domains.RestaurantCategories;
import com.br.rank.list.infra.database.documents.RestaurantCategoriesDocument;

public class RestaurantCategoriesConverter {

    public static RestaurantCategories toDomain(final RestaurantCategoriesDocument document) {
        return RestaurantCategories.builder()
                .id(document.id())
                .categories(Categories.from(document.values()))
                .code(document.code())
                .build();
    }
}
