package com.br.rank.list.infra.http.annotations.categories;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = OneWordPerCategoryValidatorAnnotation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OneWordPerCategoryValid {
    public String message() default "Cada categoria deve conter apenas uma palavra";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
