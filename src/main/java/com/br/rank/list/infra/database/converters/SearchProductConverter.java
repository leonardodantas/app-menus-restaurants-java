package com.br.rank.list.infra.database.converters;

import com.br.rank.list.domains.SearchProduct;
import com.br.rank.list.infra.database.documents.SearchProductDocument;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchProductConverter {

    public static SearchProduct toDomain(final SearchProductDocument document) {
        return SearchProduct.of(document.id(),
                document.name(),
                document.code());
    }
}
