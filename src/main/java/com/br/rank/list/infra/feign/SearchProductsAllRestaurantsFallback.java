package com.br.rank.list.infra.feign;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.domains.Product;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SearchProductsAllRestaurantsFallback implements ISearchProductsAllRestaurantsFeign {

    private final IProductRepository productRepository;

    public SearchProductsAllRestaurantsFallback(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Collection<Product> execute(final String q) {
        return productRepository.findByNameContaining(q);
    }
}
