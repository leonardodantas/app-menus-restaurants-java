package com.br.rank.list.infra.database.documents;

import com.br.rank.list.domains.Product;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Collection;

@Document("products")
public record ProductDocument(
        String id,
        String code,
        String name,
        BigDecimal price,
        CategoriesDocument categories,
        boolean promotionActive,
        PromotionDocument promotion,
        SearchInformationDocument searchInformation
) {


    public static ProductDocument from(final Product product) {
        return new ProductDocument(product.getId(), product.getCode(), product.getName(), product.getPrice(),
                CategoriesDocument.from(product.getCategories()), product.isPromotionActive(),
                PromotionDocument.from(product.getPromotion()), SearchInformationDocument.from(product.getSearchInformation())
        );
    }

    public Collection<String> categoriesValues() {
        return categories.values();
    }
}
