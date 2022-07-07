package com.br.rank.list.infra.http.annotations.categories;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CategoriesSizeValidatorAnnotation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CategoriesSizeValid {
    public String message() default "Tamanho da lista de categorias deve ser entre 1 e 5";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
