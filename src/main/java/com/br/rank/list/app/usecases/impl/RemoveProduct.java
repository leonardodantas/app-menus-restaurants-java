package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.IGetRestaurantOrThrowNotFound;
import com.br.rank.list.app.usecases.IRemoveProduct;
import com.br.rank.list.app.usecases.IRemoveProductEvents;
import org.springframework.stereotype.Service;

@Service
public class RemoveProduct implements IRemoveProduct {

    private final IProductRepository productRepository;
    private final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;
    private final IRemoveProductEvents removeProductEvents;

    public RemoveProduct(final IProductRepository productRepository, final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound, final IRemoveProductEvents removeProductEvents) {
        this.productRepository = productRepository;
        this.getRestaurantOrThrowNotFound = getRestaurantOrThrowNotFound;
        this.removeProductEvents = removeProductEvents;
    }

    @Override
    public void execute(final String id, final String code) {
        getRestaurantOrThrowNotFound.execute(code);
        productRepository.removeById(id);
        removeProductEvents.execute(id, code);
    }
}
