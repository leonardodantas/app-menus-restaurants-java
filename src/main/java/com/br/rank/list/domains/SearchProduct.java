package com.br.rank.list.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SearchProduct {

    private String id;
    private String name;
    private String code;

    private SearchProduct(final Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.code = product.getCode();
    }

    public static SearchProduct from(final Product product) {
        return new SearchProduct(product);
    }
}
