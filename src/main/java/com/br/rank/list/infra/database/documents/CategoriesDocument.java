package com.br.rank.list.infra.database.documents;


import com.br.rank.list.domains.Categories;

import java.util.Collection;

public record CategoriesDocument(
        Collection<String> values
) {

    public static CategoriesDocument from(final Categories categories) {
        return new CategoriesDocument(categories.getValues());
    }
}
