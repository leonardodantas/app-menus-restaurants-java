package com.br.rank.list.app.exceptions;

public class PromotionNotExistException extends RuntimeException {

    private PromotionNotExistException(final String message) {
        super(message);
    }

    public static PromotionNotExistException from(final String message) {
        return new PromotionNotExistException(message);
    }
}
