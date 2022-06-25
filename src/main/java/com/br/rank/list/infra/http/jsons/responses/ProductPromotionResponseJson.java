package com.br.rank.list.infra.http.jsons.responses;

import com.br.rank.list.domains.Product;

import java.math.BigDecimal;

public record ProductPromotionResponseJson(
        String id,
        String name,
        BigDecimal price,
        CategoriesResponseJson categories,
        PromotionResponse promotion
) {
    public static ProductPromotionResponseJson from(final Product product) {
        return new ProductPromotionResponseJson(
                product.getId(),
                product.getName(),
                product.getPrice(),
                CategoriesResponseJson.from(product.getCategories()),
                PromotionResponse.from(product.getPromotion())
        );
    }
}
