package com.br.rank.list.app.exceptions;

public class ProductNotFoundException extends RuntimeException {

    private ProductNotFoundException(final String message) {
        super(message);
    }

    public static ProductNotFoundException from(final String message) {
        return new ProductNotFoundException(message);
    }
}
