package com.br.rank.list.infra.jsons.responses;

import com.br.rank.list.domains.Categories;

import java.util.Collection;

public record CategoriesResponseJson(
        Collection<String> categories
) {

    public static CategoriesResponseJson from(final Categories categories) {
        return new CategoriesResponseJson(categories.getValues());
    }
}
