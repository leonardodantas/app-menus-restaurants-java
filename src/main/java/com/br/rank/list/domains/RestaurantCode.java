package com.br.rank.list.domains;


public record RestaurantCode(String code) {

    public static RestaurantCode from(final String code) {
        return new RestaurantCode(code);
    }
}
