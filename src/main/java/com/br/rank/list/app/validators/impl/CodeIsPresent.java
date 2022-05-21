package com.br.rank.list.app.validators.impl;

import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.validators.ICodeIsPresent;
import org.springframework.stereotype.Component;

@Component
public class CodeIsPresent implements ICodeIsPresent {

    private final IRestaurantRepository restaurantRepository;

    public CodeIsPresent(final IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public boolean isPresent(final String code) {
        return restaurantRepository.findByCode(code)
                .isPresent();
    }
}
