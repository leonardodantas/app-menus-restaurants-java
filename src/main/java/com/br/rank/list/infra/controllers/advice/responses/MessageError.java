package com.br.rank.list.infra.controllers.advice.responses;

import org.springframework.validation.FieldError;

public record MessageError(String message) {

    public static MessageError from(final RuntimeException error) {
        return new MessageError(error.getMessage());
    }

    public static MessageError of(final FieldError field, final String message) {
        return new MessageError(field.getField().concat(" - ".concat(message)));
    }
}