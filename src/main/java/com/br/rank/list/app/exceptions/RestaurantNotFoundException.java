package com.br.rank.list.app.exceptions;

public class RestaurantNotFoundException extends RuntimeException {

    private RestaurantNotFoundException(final String message) {
        super(message);
    }

    public static RestaurantNotFoundException from(final String message) {
        return new RestaurantNotFoundException(message);
    }
}
