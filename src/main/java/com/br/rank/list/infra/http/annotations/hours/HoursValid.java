package com.br.rank.list.infra.http.annotations.hours;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = HoursValidatorAnnotation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface HoursValid {
    public String message() default "Hora deve estar no formato HH:mm";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
