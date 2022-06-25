package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.exceptions.PromotionNotExistException;
import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.usecases.IGetProductOrThrowNotFound;
import com.br.rank.list.app.usecases.IRemovePromotionDay;
import com.br.rank.list.domains.DayAndHour;
import com.br.rank.list.domains.Days;
import com.br.rank.list.domains.Product;
import org.springframework.stereotype.Service;

@Service
public class RemovePromotionDay implements IRemovePromotionDay {

    private final IProductRepository productRepository;

    private final IGetProductOrThrowNotFound getProductOrThrowNotFound;

    public RemovePromotionDay(final IProductRepository productRepository, final IGetProductOrThrowNotFound getProductOrThrowNotFound) {
        this.productRepository = productRepository;
        this.getProductOrThrowNotFound = getProductOrThrowNotFound;
    }

    @Override
    public void execute(final String productId, final Days day) {
        final var product = getProductOrThrowNotFound.execute(productId);
        final DayAndHour dayAndHourPromotion = getDayAndHour(product, day);
        product.removePromotionOf(dayAndHourPromotion);
        productRepository.save(product);
    }

    private DayAndHour getDayAndHour(final Product product, final Days day) {
        return product.getPromotion()
                .getDayAndHours()
                .stream()
                .filter(dayAndHour -> dayAndHour.getDay().equals(day))
                .findFirst()
                .orElseThrow(() -> PromotionNotExistException.from("No promotions for the informed day"));
    }
}
