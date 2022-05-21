package com.br.rank.list.infra.converters;

import com.br.rank.list.domains.Categories;
import com.br.rank.list.domains.Product;
import com.br.rank.list.domains.Promotion;
import com.br.rank.list.infra.jsons.requests.CategoriesRequestJson;
import com.br.rank.list.infra.jsons.requests.ProductRequestJson;

public class ProductConverter {

    public static Product toDomain(final ProductRequestJson json){
        return Product.builder()
                .name(json.name())
                .code(json.code())
                .categories(toDomain(json.categories()))
                .price(json.price())
                .build();
    }

    private static Categories toDomain(final CategoriesRequestJson json){
        return Categories.from(json.values());
    }
}
