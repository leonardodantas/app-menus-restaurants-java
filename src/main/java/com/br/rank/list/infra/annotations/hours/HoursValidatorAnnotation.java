package com.br.rank.list.infra.annotations.hours;

import com.br.rank.list.app.validators.IHoursValid;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class HoursValidatorAnnotation implements ConstraintValidator<HoursValid, String> {

    private final IHoursValid hoursValid;

    public HoursValidatorAnnotation(final IHoursValid hoursValid) {
        this.hoursValid = hoursValid;
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if(Strings.isNullOrEmpty(value)) {
            return false;
        }
        return this.hoursValid.isValid(value);
    }
}
