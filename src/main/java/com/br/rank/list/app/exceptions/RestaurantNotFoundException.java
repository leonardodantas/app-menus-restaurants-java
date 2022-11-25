package com.br.rank.list.app.exceptions;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(final String message) {
        super(message);
    }

}
