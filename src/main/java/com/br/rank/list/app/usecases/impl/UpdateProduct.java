package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.exceptions.ProductNotFoundException;
import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.IGetRestaurantOrThrowNotFound;
import com.br.rank.list.app.usecases.IUpdateProduct;
import com.br.rank.list.domains.Product;
import com.br.rank.list.domains.RestaurantCode;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UpdateProduct implements IUpdateProduct {

    private final IProductRepository productRepository;
    private final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UpdateProduct(final IProductRepository productRepository, final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound, final ApplicationEventPublisher applicationEventPublisher) {
        this.productRepository = productRepository;
        this.getRestaurantOrThrowNotFound = getRestaurantOrThrowNotFound;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void execute(final String id, final Product product) {
        getRestaurantOrThrowNotFound.execute(product.getCode());

        final var productSave = productRepository.findById(id)
                .map(p -> productRepository.save(product.fromId(p.getId())))
                .orElseThrow(() -> ProductNotFoundException.from("Product not found"));

        applicationEventPublisher.publishEvent(productSave);
        applicationEventPublisher.publishEvent(RestaurantCode.from(productSave.getCode()));
    }
}
