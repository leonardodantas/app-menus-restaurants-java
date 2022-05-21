package com.br.rank.list.infra.annotations.cnpj;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CNPJUniqueValidatorAnnotation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CNPJUniqueValid {
    public String message() default "CNPJ não pode ter sido cadastrado anteriormente";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
