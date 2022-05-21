package com.br.rank.list.app.exceptions;

public class PromotionAlreadyExistException extends RuntimeException {

    private PromotionAlreadyExistException(final String message) {
        super(message);
    }

    public static PromotionAlreadyExistException from(final String message) {
        return new PromotionAlreadyExistException(message);
    }
}
