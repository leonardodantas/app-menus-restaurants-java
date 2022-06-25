package com.br.rank.list.infra.http.annotations.categories;

import com.br.rank.list.app.validators.ICategoriesValidator;
import com.br.rank.list.infra.http.jsons.requests.CategoriesRequestJson;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Component
public class RepeatedCategoriesValidatorAnnotation implements ConstraintValidator<RepeatedCategoriesValid, CategoriesRequestJson> {

    private final ICategoriesValidator categoriesValidator;

    public RepeatedCategoriesValidatorAnnotation(final ICategoriesValidator categoriesValidator) {
        this.categoriesValidator = categoriesValidator;
    }

    @Override
    public boolean isValid(final CategoriesRequestJson value, final ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return false;
        }
        return categoriesValidator.validRepeatedCategories(value);
    }
}
