package com.br.rank.list.app.validators.impl;

import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.validators.ICNPJUnique;
import org.springframework.stereotype.Component;

@Component
public class CNPJUnique implements ICNPJUnique {

    private final IRestaurantRepository restaurantRepository;

    public CNPJUnique(final IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public boolean isCNPJUnique(final String cnpj) {
        return restaurantRepository.findByCnpj(cnpj.replaceAll("\\D", ""))
                .isEmpty();
    }
}
