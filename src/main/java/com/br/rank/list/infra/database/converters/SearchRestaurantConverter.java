package com.br.rank.list.infra.database.converters;

import com.br.rank.list.domains.SearchRestaurant;
import com.br.rank.list.infra.database.documents.SearchRestaurantDocument;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchRestaurantConverter {

    public static SearchRestaurant toDomain(final SearchRestaurantDocument document) {
        return SearchRestaurant.of(
                document.id(),
                document.name());
    }
}
