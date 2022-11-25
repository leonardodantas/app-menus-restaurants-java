package com.br.rank.list.app.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(final String message) {
        super(message);
    }

}
