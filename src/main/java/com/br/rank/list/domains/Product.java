package com.br.rank.list.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("products")
public class Product implements Serializable {

    private String id;
    private String code;
    private String name;
    private BigDecimal price;
    private Categories categories;
    private boolean promotionActive;
    private Promotion promotion;

    public static Product createPromotionOf(final Product product, final Promotion promotion) {
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

    public static Product of(final Product product, final Collection<DayAndHour> daysAndHours) {
        return new Product(product.getId(), product.getCode(), product.getName(), product.getPrice(), product.getCategories(), true, Promotion.of(product.getPromotion(), daysAndHours));
    }

    public Product fromId(final String id) {
        return new Product(id, this.code, this.name, this.price, this.categories, this.promotionActive, this.promotion);
    }

    public void removePromotion() {
        this.promotionActive = false;
        this.promotion = Promotion.builder().build();
    }


    public void removePromotion(final DayAndHour dayAndHour) {
        this.promotion.getDayAndHours().remove(dayAndHour);
        if (promotion.getDayAndHours().size() == 0) {
            this.removePromotion();
        }
    }

    public BigDecimal getPrice() {
        final var dayAndHour = checkForPromotion();
        dayAndHour.ifPresent((dH) -> this.applyPromotion());
        return price;
    }

    private Optional<DayAndHour> checkForPromotion() {
        return Optional.ofNullable(this.getPromotion())
                .orElse(new Promotion())
                .getDayAndHours()
                .stream().filter(dayAndHour -> {
                    final var day = DayOfWeek.from(LocalDate.now()).name();
                    return dayAndHour.getDay().equalsIgnoreCase(day);
                }).findFirst();
    }

    public void applyPromotion() {
        this.price = promotion.getPromotionalPrice();
    }
}
