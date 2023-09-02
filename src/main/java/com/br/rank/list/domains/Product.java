package com.br.rank.list.domains;

import com.br.rank.list.app.exceptions.PromotionAlreadyExistException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Getter
@NoArgsConstructor
public final class Product implements Serializable {

    private String id;
    private String code;
    private String name;
    private BigDecimal price;
    private Categories categories;
    private boolean promotionActive;
    private Promotion promotion;

    private Product(final String id, final String code, final String name, final BigDecimal price, final Categories categories, final boolean promotionActive, final Promotion promotion) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.categories = categories;
        this.promotionActive = promotionActive;
        this.promotion = promotion;
    }

    public static Product createPromotionOf(final Product product, final Promotion promotion) {
        validateDaysPromotion(product.getPromotion(), promotion);
        return new Product(
                product.getId(),
                product.getCode(),
                product.getName(),
                product.getPrice(),
                product.getCategories(),
                true,
                promotion
        );
    }

    private static void validateDaysPromotion(final Promotion newPromotion, final Promotion promotionActual) {
        promotionActual.getDayAndHours()
                .stream().filter(existingPromotions -> newPromotion
                        .getDayAndHours()
                        .stream()
                        .anyMatch(newPromotions -> newPromotions.getDay().equals(existingPromotions.getDay())))
                .findFirst()
                .ifPresent(p -> {
                    throw new PromotionAlreadyExistException("Promotion already exists to day " + p.getDay());
                });
    }

    public static Product of(final Product product, final Collection<DayAndHour> daysAndHours) {
        return new Product(product.getId(), product.getCode(), product.getName(), product.getPrice(), product.getCategories(), true, Promotion.of(product.getPromotion(), daysAndHours));
    }

    public void removePromotion() {
        this.promotionActive = false;
        this.promotion = Promotion.noPromotion();
    }


    public void removePromotionOf(final DayAndHour dayAndHour) {
        this.promotion.getDayAndHours().remove(dayAndHour);
        if (promotion.getDayAndHours().isEmpty()) {
            this.removePromotion();
        }
    }

    public BigDecimal getPrice() {
        final var dayAndHour = checkForPromotion();
        dayAndHour.ifPresent(dH -> this.applyPromotion());
        return price;
    }

    private Optional<DayAndHour> checkForPromotion() {
        return Optional.ofNullable(this.getPromotion())
                .orElse(Promotion.noPromotion())
                .getDayAndHours()
                .stream().filter(dayAndHour -> {
                    final var day = DayOfWeek.from(LocalDate.now()).name();
                    return dayAndHour.getDay().equalsIgnoreCase(day);
                }).findFirst();
    }

    private void applyPromotion() {
        this.price = promotion.getPromotionalPrice();
    }

    public Product withId(final String id) {
        return new Product(
                id,
                this.code,
                this.name,
                this.price,
                this.categories,
                this.promotionActive,
                this.promotion
        );
    }

    public static Builder builder(final String name, final String code, final Categories categories, final BigDecimal price) {
        return new Builder(name, code, categories, price);
    }

    public static class Builder {

        private String id;
        private final String name;
        private final String code;
        private final Categories categories;
        private final BigDecimal price;
        private boolean promotionActive;
        private Promotion promotion;

        public Builder(final String name, final String code, final Categories categories, final BigDecimal price) {
            this.name = name;
            this.code = code;
            this.categories = categories;
            this.price = price;
            this.promotionActive = false;
            this.promotion = Promotion.noPromotion();
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }


        public Builder promotion(final Promotion promotion) {
            this.promotion = promotion;
            return this;
        }


        public Builder promotionActive(final boolean promotionActive) {
            this.promotionActive = promotionActive;
            return this;
        }

        public Product build() {
            return new Product(id, code, name, price, categories, promotionActive, promotion);
        }
    }
}
