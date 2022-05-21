package com.br.rank.list.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document("searchProduct")
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
