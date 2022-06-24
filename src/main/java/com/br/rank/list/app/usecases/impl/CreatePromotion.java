package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.exceptions.PromotionAlreadyExistException;
import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.ICreatePromotion;
import com.br.rank.list.app.usecases.IGetProductOrThrowNotFound;
import com.br.rank.list.domains.Product;
import com.br.rank.list.domains.Promotion;
import org.springframework.stereotype.Service;

@Service
public class CreatePromotion implements ICreatePromotion {

    private final IProductRepository productRepository;

    private final IGetProductOrThrowNotFound getProductOrThrowNotFound;

    public CreatePromotion(final IProductRepository productRepository, final IRestaurantRepository restaurantRepository, final IGetProductOrThrowNotFound getProductOrThrowNotFound) {
        this.productRepository = productRepository;
        this.getProductOrThrowNotFound = getProductOrThrowNotFound;
    }

    @Override
    public Product execute(final String productId, final Promotion promotion) {
        final var product = getProductOrThrowNotFound.execute(productId);
        validateDaysPromotion(product, promotion);
        final var productToUpdate = Product.createPromotionOf(product, promotion);
        return productRepository.saveProductWithPromotion(productToUpdate);
    }

    private void validateDaysPromotion(final Product product, final Promotion promotion) {
        final var promotions = product.getPromotion();
        promotions.operationTimeIsValid();

        promotions.getDayAndHours()
                .stream().filter(existingPromotions -> promotion
                        .getDayAndHours()
                        .stream()
                        .anyMatch(newPromotions -> newPromotions.getDay().equals(existingPromotions.getDay())))
                .findFirst()
                .ifPresent(p -> {
                    throw PromotionAlreadyExistException.from("Promotion already exists to day " + p.getDay());
                });
    }
}
