package com.br.rank.list.infra.annotations.code;

import com.br.rank.list.app.validators.ICodeUnique;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CodeUniqueValidatorAnnotation implements ConstraintValidator<CodeUniqueValid, String> {

    private final ICodeUnique codeUnique;

    public CodeUniqueValidatorAnnotation(final ICodeUnique codeUnique) {
        this.codeUnique = codeUnique;
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (Strings.isNullOrEmpty(value)) {
            return false;
        }
        return codeUnique.isCodeUnique(value);
    }
}
