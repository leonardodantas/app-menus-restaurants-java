package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.ICreateProduct;
import com.br.rank.list.app.usecases.ICreateProductEvents;
import com.br.rank.list.app.usecases.IGetRestaurantOrThrowNotFound;
import com.br.rank.list.domains.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class CreateProduct implements ICreateProduct {

    private final IProductRepository productRepository;
    private final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    private final ICreateProductEvents createProductEvents;

    public CreateProduct(final IProductRepository productRepository, final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound, final ICreateProductEvents createProductEvents) {
        this.productRepository = productRepository;
        this.getRestaurantOrThrowNotFound = getRestaurantOrThrowNotFound;
        this.createProductEvents = createProductEvents;
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public Product execute(final Product product) {
        getRestaurantOrThrowNotFound.execute(product.getCode());
        final var productSave = productRepository.save(product);
        createProductEvents.execute(productSave);
        return productSave;
    }
}
