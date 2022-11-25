package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.exceptions.ProductNotFoundException;
import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.IGetProductOrThrowNotFound;
import com.br.rank.list.app.usecases.IGetRestaurantOrThrowNotFound;
import com.br.rank.list.domains.Product;
import org.springframework.stereotype.Service;

@Service
public class GetProductOrThrowNotFound implements IGetProductOrThrowNotFound {

    private final IProductRepository productRepository;
    private final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    public GetProductOrThrowNotFound(final IProductRepository productRepository, final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound) {
        this.productRepository = productRepository;
        this.getRestaurantOrThrowNotFound = getRestaurantOrThrowNotFound;
    }

    @Override
    public Product execute(final String productId) {
        final var product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        getRestaurantOrThrowNotFound.execute(product.getCode());

        return product;
    }
}
