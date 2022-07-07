package com.br.rank.list.infra.database.documents;

import com.br.rank.list.domains.Promotion;

import java.math.BigDecimal;
import java.util.Collection;

public record PromotionDocument(
        String description,
        BigDecimal promotionalPrice,
        Collection<DayAndHourDocument> dayAndHours
) {

    public static PromotionDocument from(final Promotion promotion) {
        final var daysAndHours = promotion.getDayAndHours().stream().map(DayAndHourDocument::from).toList();
        return new PromotionDocument(promotion.getDescription(), promotion.getPromotionalPrice(), daysAndHours);
    }
}
