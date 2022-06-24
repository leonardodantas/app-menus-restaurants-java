package com.br.rank.list.infra.database.converters;

import com.br.rank.list.domains.SearchRestaurant;
import com.br.rank.list.infra.database.documents.SearchRestaurantDocument;

public class SearchRestaurantConverter {

    public static SearchRestaurant toDomain(final SearchRestaurantDocument document) {
        return SearchRestaurant.builder()
                .id(document.id())
                .name(document.name())
                .build();
    }
}
