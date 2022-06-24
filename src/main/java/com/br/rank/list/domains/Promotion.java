package com.br.rank.list.domains;

import com.br.rank.list.app.exceptions.TimeBetweenHoursException;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Getter
@Builder
public class Promotion implements Serializable {
    private String description;
    private BigDecimal promotionalPrice;
    private Collection<DayAndHour> dayAndHours;

    public Promotion() {

    }

    private Promotion(final String description, final BigDecimal promotionalPrice, final Collection<DayAndHour> dayAndHours) {
        this.description = description;
        this.promotionalPrice = promotionalPrice;
        this.dayAndHours = dayAndHours;
    }

    public static Promotion of(final Promotion promotion, final Collection<DayAndHour> dayAndHours) {
        return new Promotion(promotion.getDescription(), promotion.getPromotionalPrice(), dayAndHours);
    }

    public Collection<DayAndHour> getDayAndHours() {
        return Optional.ofNullable(dayAndHours)
                .orElse(List.of());
    }

    public void operationTimeIsValid() {
        final var invalid = getDayAndHours().stream().anyMatch(
                dayAndHour -> dayAndHour.getOperatingTime() < 15
        );

        if (invalid) {
            throw TimeBetweenHoursException.from("Operation time promotion " + this.description + " is less than 15 minutes");
        }
    }
}
