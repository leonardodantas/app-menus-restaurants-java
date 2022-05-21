package com.br.rank.list.infra.annotations.code;

import com.br.rank.list.app.validators.ICodeIsPresent;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CodeIsPresentValidatorAnnotation implements ConstraintValidator<CodeIsPresentValid, String> {

    private final ICodeIsPresent codeIsPresent;

    public CodeIsPresentValidatorAnnotation(final ICodeIsPresent codeIsPresent) {
        this.codeIsPresent = codeIsPresent;
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return codeIsPresent.isPresent(value);
    }
}
