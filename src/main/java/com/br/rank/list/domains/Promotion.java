package com.br.rank.list.domains;

import com.br.rank.list.domains.exceptions.TimeBetweenHoursException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
public final class  Promotion implements Serializable {
    private String description;
    private BigDecimal promotionalPrice;
    private Collection<DayAndHour> dayAndHours;

    private Promotion(final String description, final BigDecimal promotionalPrice, final Collection<DayAndHour> dayAndHours) {
        this.description = description;
        this.promotionalPrice = promotionalPrice;
        this.dayAndHours = dayAndHours;

        this.validateOperationTime();
    }

    public static Promotion noPromotion() {
        return new Promotion();
    }

    public static Promotion of(final String description, final BigDecimal promotionalPrice, Collection<DayAndHour> dayAndHours) {
        return new Promotion(description, promotionalPrice, dayAndHours);
    }

    public static Promotion of(final Promotion promotion, final Collection<DayAndHour> dayAndHours) {
        return new Promotion(promotion.getDescription(), promotion.getPromotionalPrice(), dayAndHours);
    }

    public Collection<DayAndHour> getDayAndHours() {
        return Optional.ofNullable(dayAndHours)
                .orElse(List.of());
    }

    private void validateOperationTime() {
        final var invalid = getDayAndHours().stream().anyMatch(
                dayAndHour -> dayAndHour.getOperatingTime() < 15
        );

        if (invalid) {
            throw new TimeBetweenHoursException("Operation time promotion " + this.description + " is less than 15 minutes");
        }
    }
}
