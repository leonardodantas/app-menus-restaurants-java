package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.IFindAllPromotionsRestaurant;
import com.br.rank.list.domains.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FindAllPromotionsRestaurant implements IFindAllPromotionsRestaurant {

    private final IProductRepository productRepository;

    public FindAllPromotionsRestaurant(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Collection<Product> execute(final String code) {
        return productRepository.findByCodeAndPromotionTrue(code);
    }
}
