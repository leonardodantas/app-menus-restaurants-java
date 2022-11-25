package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.exceptions.ProductNotFoundException;
import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.IGetRestaurantOrThrowNotFound;
import com.br.rank.list.app.usecases.IUpdateProduct;
import com.br.rank.list.app.usecases.IUpdateProductEvents;
import com.br.rank.list.domains.Product;
import org.springframework.stereotype.Service;

@Service
public class UpdateProduct implements IUpdateProduct {

    private final IProductRepository productRepository;
    private final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;
    private final IUpdateProductEvents updateProductEvents;

    public UpdateProduct(final IProductRepository productRepository, final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound, final IUpdateProductEvents updateProductEvents) {
        this.productRepository = productRepository;
        this.getRestaurantOrThrowNotFound = getRestaurantOrThrowNotFound;
        this.updateProductEvents = updateProductEvents;
    }

    @Override
    public void execute(final String id, final Product product) {
        getRestaurantOrThrowNotFound.execute(product.getCode());

        final var productSave = productRepository.findById(id)
                .map(p -> productRepository.save(product.withId(p.getId())))
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        updateProductEvents.execute(productSave);
    }
}
