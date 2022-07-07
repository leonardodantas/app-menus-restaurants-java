package com.br.rank.list.infra.http.annotations.hours;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = OperatingHoursValidatorAnnotation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OperatingHoursValid {
    public String message() default "Data final deve ser maior que a data inicial";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
