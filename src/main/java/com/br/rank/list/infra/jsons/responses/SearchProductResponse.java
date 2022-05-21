package com.br.rank.list.infra.jsons.responses;

import com.br.rank.list.domains.SearchProduct;

public record SearchProductResponse(
        String id,
        String name
) {

    public static SearchProductResponse from(final SearchProduct searchProduct) {
        return new SearchProductResponse(searchProduct.getId(), searchProduct.getName());
    }
}
