package com.br.rank.list.app.validators.impl;

import com.br.rank.list.app.validators.ICategoriesValidator;
import com.br.rank.list.infra.jsons.requests.CategoriesRequestJson;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class CategoriesValidator implements ICategoriesValidator {

    @Override
    public boolean validSize(final CategoriesRequestJson categories) {
        return categories.values()
                .size() > 0 && categories.values()
                .size() < 6;
    }

    @Override
    public boolean validOneWordsPerCategory(final CategoriesRequestJson categories) {
        final var valid = categories.values()
                .stream()
                .anyMatch(category -> category.split(" ").length > 1);

        return !valid;
    }

    @Override
    public boolean validRepeatedCategories(final CategoriesRequestJson categoriesRequestJson) {
        final var initialSize = categoriesRequestJson.values().size();
        final var sizeWithoutRepetition = new HashSet<>(categoriesRequestJson.values());
        return initialSize == sizeWithoutRepetition.size();
    }
}
