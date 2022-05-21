package com.br.rank.list.app.validators.impl;

import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.validators.ICodeUnique;
import org.springframework.stereotype.Component;

@Component
public class CodeUnique implements ICodeUnique {

    private final IRestaurantRepository restaurantRepository;

    public CodeUnique(final IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public boolean isCodeUnique(final String code) {
        return restaurantRepository.findByCode(code).isEmpty();
    }
}
