package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.IGetRestaurantOrThrowNotFound;
import com.br.rank.list.app.usecases.IRemoveProduct;
import com.br.rank.list.domains.RestaurantCode;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class RemoveProduct implements IRemoveProduct {

    private final IProductRepository productRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    public RemoveProduct(final IProductRepository productRepository, final ApplicationEventPublisher applicationEventPublisher, final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound) {
        this.productRepository = productRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.getRestaurantOrThrowNotFound = getRestaurantOrThrowNotFound;
    }

    @Override
    public void execute(final String id, final String code) {
        getRestaurantOrThrowNotFound.execute(code);
        productRepository.removeById(id);
        applicationEventPublisher.publishEvent(RestaurantCode.from(code));
    }
}
