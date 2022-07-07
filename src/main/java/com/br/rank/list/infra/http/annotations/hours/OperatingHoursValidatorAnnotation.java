package com.br.rank.list.infra.http.annotations.hours;

import com.br.rank.list.infra.http.jsons.requests.OperatingHoursRequestJson;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Component
public class OperatingHoursValidatorAnnotation implements ConstraintValidator<OperatingHoursValid, OperatingHoursRequestJson> {

    @Override
    public boolean isValid(OperatingHoursRequestJson value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return false;
        }
        return true;
//        return value.startTime().compareTo(value.endTime()) < 0;
    }
}
