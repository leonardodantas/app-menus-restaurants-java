package com.br.rank.list.infra.jsons.requests;

import com.br.rank.list.infra.annotations.categories.CategoriesSizeValid;
import com.br.rank.list.infra.annotations.categories.OneWordPerCategoryValid;
import com.br.rank.list.infra.annotations.categories.RepeatedCategoriesValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public record ProductRequestJson(
        @NotBlank
        String code,
        @Size(min = 5, max = 120)
        String name,
        @Positive
        BigDecimal price,
        @CategoriesSizeValid
        @OneWordPerCategoryValid
        @RepeatedCategoriesValid
        CategoriesRequestJson categories) {

}
