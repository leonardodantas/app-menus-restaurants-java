package com.br.rank.list.infra.jsons.responses;

import com.br.rank.list.domains.Product;

import java.math.BigDecimal;

public record ProductResponseJson(
        String id,
        String name,
        BigDecimal price,
        CategoriesResponseJson categories
) {
    public static ProductResponseJson from(final Product product) {
        return new ProductResponseJson(product.getId(), product.getName(), product.getPrice(),
                CategoriesResponseJson.from(product.getCategories()));
    }
}
