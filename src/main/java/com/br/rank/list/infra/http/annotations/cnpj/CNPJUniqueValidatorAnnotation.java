package com.br.rank.list.infra.http.annotations.cnpj;

import com.br.rank.list.app.validators.ICNPJUnique;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CNPJUniqueValidatorAnnotation implements ConstraintValidator<CNPJUniqueValid, String> {

    private final ICNPJUnique cnpjUnique;

    public CNPJUniqueValidatorAnnotation(final ICNPJUnique cnpjUnique) {
        this.cnpjUnique = cnpjUnique;
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (Strings.isNullOrEmpty(value)) {
            return false;
        }
        return cnpjUnique.isCNPJUnique(value);
    }
}
