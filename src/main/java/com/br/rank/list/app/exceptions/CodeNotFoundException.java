package com.br.rank.list.app.exceptions;

public class CodeNotFoundException extends RuntimeException {

    private CodeNotFoundException(final String message) {
        super(message);
    }

    public static CodeNotFoundException from(final String message) {
        return new CodeNotFoundException(message);
    }
}
