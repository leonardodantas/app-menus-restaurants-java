package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.ICreatePromotion;
import com.br.rank.list.app.usecases.IGetProductOrThrowNotFound;
import com.br.rank.list.domains.Product;
import com.br.rank.list.domains.Promotion;
import org.springframework.stereotype.Service;

@Service
public class CreatePromotion implements ICreatePromotion {

    private final IProductRepository productRepository;

    private final IGetProductOrThrowNotFound getProductOrThrowNotFound;

    public CreatePromotion(final IProductRepository productRepository, final IGetProductOrThrowNotFound getProductOrThrowNotFound) {
        this.productRepository = productRepository;
        this.getProductOrThrowNotFound = getProductOrThrowNotFound;
    }

    @Override
    public Product execute(final String productId, final Promotion promotion) {
        final var product = getProductOrThrowNotFound.execute(productId);
        final var productToUpdate = Product.createPromotionOf(product, promotion);
        return productRepository.saveProductWithPromotion(productToUpdate);
    }

}
