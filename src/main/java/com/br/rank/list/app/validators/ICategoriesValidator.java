package com.br.rank.list.app.validators;

import com.br.rank.list.infra.http.jsons.requests.CategoriesRequestJson;

public interface ICategoriesValidator {
    boolean validSize(final CategoriesRequestJson categoriesRequestJson);
    boolean validOneWordsPerCategory(CategoriesRequestJson categoriesRequestJson);
    boolean validRepeatedCategories(CategoriesRequestJson categoriesRequestJson);
}
