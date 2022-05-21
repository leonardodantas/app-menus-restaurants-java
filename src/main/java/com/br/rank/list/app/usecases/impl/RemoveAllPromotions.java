package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.IGetProductOrThrowNotFound;
import com.br.rank.list.app.usecases.IRemoveAllPromotions;
import org.springframework.stereotype.Service;

@Service
public class RemoveAllPromotions implements IRemoveAllPromotions {

    private final IProductRepository productRepository;
    private final IGetProductOrThrowNotFound getProductOrThrowNotFound;

    public RemoveAllPromotions(final IProductRepository productRepository, final IGetProductOrThrowNotFound getProductOrThrowNotFound) {
        this.productRepository = productRepository;
        this.getProductOrThrowNotFound = getProductOrThrowNotFound;
    }

    @Override
    public void execute(final String productId) {
        final var product = getProductOrThrowNotFound.execute(productId);
        product.removePromotion();
        productRepository.save(product);
    }
}
