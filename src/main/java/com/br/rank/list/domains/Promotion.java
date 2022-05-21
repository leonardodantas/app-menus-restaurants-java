package com.br.rank.list.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Promotion implements Serializable {

    private String description;
    private BigDecimal promotionalPrice;
    private Collection<DayAndHour> dayAndHours;

    public static Promotion of(final Promotion promotion, final Collection<DayAndHour> dayAndHours) {
        return new Promotion(promotion.getDescription(), promotion.getPromotionalPrice(), dayAndHours);
    }

    public Collection<DayAndHour> getDayAndHours() {
        return Optional.ofNullable(dayAndHours)
                .orElse(List.of());
    }
}
