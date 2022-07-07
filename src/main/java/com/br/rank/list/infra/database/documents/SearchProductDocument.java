package com.br.rank.list.infra.database.documents;

import com.br.rank.list.domains.SearchProduct;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("searchProduct")
public record SearchProductDocument(
        String id,
        String name,
        String code
) {

    public static SearchProductDocument from(final SearchProduct searchProduct) {
        return new SearchProductDocument(searchProduct.getId(), searchProduct.getName(), searchProduct.getCode());
    }
}
