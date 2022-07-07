package com.br.rank.list.domains;

import lombok.Getter;

@Getter
public final class SearchProduct {

    private String id;
    private String name;
    private String code;

    private SearchProduct(final Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.code = product.getCode();
    }

    private SearchProduct(final String id, final String name, final String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public static SearchProduct from(final Product product) {
        return new SearchProduct(product);
    }

    public static SearchProduct of(final String id, final String name, final String code) {
        return new SearchProduct(id, name, code);
    }
}
