package com.br.rank.list.infra.database.converters;

import com.br.rank.list.domains.SearchProduct;
import com.br.rank.list.infra.database.documents.SearchProductDocument;

public class SearchProductConverter {

    public static SearchProduct toDomain(final SearchProductDocument document) {
        return SearchProduct.builder()
                .id(document.id())
                .code(document.code())
                .name(document.name())
                .build();
    }
}
