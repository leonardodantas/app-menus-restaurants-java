package com.br.rank.list.infra.annotations.categories;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RepeatedCategoriesValidatorAnnotation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RepeatedCategoriesValid {
    public String message() default "Categorias não podem ser repetidas";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
