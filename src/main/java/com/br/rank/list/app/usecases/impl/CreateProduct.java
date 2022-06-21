package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.messages.ISendProductMessage;
import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.ICreateProduct;
import com.br.rank.list.app.usecases.IGetRestaurantOrThrowNotFound;
import com.br.rank.list.domains.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CreateProduct implements ICreateProduct {

    private final IProductRepository productRepository;
    private final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ISendProductMessage sendProductMessage;

    public CreateProduct(final IProductRepository productRepository, final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound, final ApplicationEventPublisher applicationEventPublisher, final ISendProductMessage sendProductMessage) {
        this.productRepository = productRepository;
        this.getRestaurantOrThrowNotFound = getRestaurantOrThrowNotFound;
        this.applicationEventPublisher = applicationEventPublisher;
        this.sendProductMessage = sendProductMessage;
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public Product execute(final Product product) {
        getRestaurantOrThrowNotFound.execute(product.getCode());
        applicationEventPublisher.publishEvent(product);
        final var productSave = productRepository.save(product);
        sendProductMessage.execute(productSave);
        return productSave;
    }
}
