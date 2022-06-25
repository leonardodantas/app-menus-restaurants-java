package com.br.rank.list.infra.http.annotations.code;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CodeIsPresentValidatorAnnotation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CodeIsPresentValid {
    public String message() default "Codigo precisa estar previamente cadastrado";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
