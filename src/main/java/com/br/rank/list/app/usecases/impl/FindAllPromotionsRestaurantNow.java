package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.repositories.IProductRepository;
import com.br.rank.list.app.repositories.IRestaurantRepository;
import com.br.rank.list.app.usecases.IFindAllPromotionsRestaurantNow;
import com.br.rank.list.app.usecases.IGetRestaurantOrThrowNotFound;
import com.br.rank.list.domains.DayAndHour;
import com.br.rank.list.domains.Product;
import com.br.rank.list.domains.Promotion;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Optional;

@Service
public class FindAllPromotionsRestaurantNow implements IFindAllPromotionsRestaurantNow {

    private final IProductRepository productRepository;
    private final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound;

    public FindAllPromotionsRestaurantNow(final IProductRepository productRepository, final IRestaurantRepository restaurantRepository, final IGetRestaurantOrThrowNotFound getRestaurantOrThrowNotFound) {
        this.productRepository = productRepository;
        this.getRestaurantOrThrowNotFound = getRestaurantOrThrowNotFound;
    }

    @Override
    public Collection<Product> execute(final String code) {
        getRestaurantOrThrowNotFound.execute(code);
        final var products = productRepository.findAllByCodeAndPromotionTrueCacheable(code);

        return products.stream()
                .filter(this::findEligibleProducts)
                .map(this::getProducts)
                .toList();
    }

    private Product getProducts(final Product product) {
        final var daysAndHours = product.getPromotion().
                getDayAndHours()
                .stream().filter(dayAndHour -> dayAndHour.getDay().equalsIgnoreCase(this.getDay())).toList();

        return Product.of(product, daysAndHours);
    }

    private boolean findEligibleProducts(final Product product) {
        final var promotion = product.getPromotion();

        final var dayAndHourOptional = getDayAndHour(promotion);

        if (dayAndHourOptional.isPresent()) {
            final var hour = LocalTime.now();
            return dayAndHourOptional.get().getStartTime().isBefore(hour) && dayAndHourOptional.get().getEndTime().isAfter(hour);
        }

        return false;
    }

    private Optional<DayAndHour> getDayAndHour(final Promotion promotion) {

        return promotion.getDayAndHours()
                .stream()
                .filter(dayAndHour -> dayAndHour.getDay().equalsIgnoreCase(this.getDay()))
                .findFirst();
    }

    private String getDay() {
        return DayOfWeek.from(LocalDate.now()).name();
    }
}
