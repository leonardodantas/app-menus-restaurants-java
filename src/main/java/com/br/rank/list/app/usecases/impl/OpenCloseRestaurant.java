package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.domains.Restaurant;

public enum OpenCloseRestaurant {

    OPEN("open") {
        @Override
        Restaurant execute(final Restaurant restaurant) {
            return restaurant.open();
        }
    }, CLOSE("close") {
        @Override
        Restaurant execute(final Restaurant restaurant) {
            return restaurant.close();
        }
    };

    private final String name;

    OpenCloseRestaurant(final String name) {
        this.name = name;
    }

    abstract Restaurant execute(final Restaurant restaurant);
}
