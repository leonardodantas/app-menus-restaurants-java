package com.br.rank.list.infra.http.converters;

import com.br.rank.list.domains.Categories;
import com.br.rank.list.domains.Product;
import com.br.rank.list.infra.http.jsons.requests.CategoriesRequestJson;
import com.br.rank.list.infra.http.jsons.requests.ProductRequestJson;

public class ProductConverter {

    public static Product toDomain(final ProductRequestJson json) {
        return Product.builder(json.name(), json.code(), toDomain(json.categories()), json.price()).build();
    }

    private static Categories toDomain(final CategoriesRequestJson json) {
        return Categories.from(json.values());
    }
}
