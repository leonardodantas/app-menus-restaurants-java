package com.br.rank.list.infra.database.converters;

import com.br.rank.list.domains.*;
import com.br.rank.list.infra.database.documents.ProductDocument;
import com.br.rank.list.infra.database.documents.PromotionDocument;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter {

    public static Product toDomain(final ProductDocument productDocument) {
        return Product.builder(productDocument.name(), productDocument.code(), Categories.from(productDocument.categoriesValues()), productDocument.price())
                .id(productDocument.id())
                .promotionActive(productDocument.promotionActive())
                .promotion(toDomain(productDocument.promotion()))
                .build();
    }

    private static Promotion toDomain(final PromotionDocument promotionDocument) {

        final var daysAndHours = promotionDocument.dayAndHours().stream().map(dayAndHourDocument ->
                DayAndHour.of(Days.valueOf(dayAndHourDocument.day().name()),
                        dayAndHourDocument.startTime(),
                        dayAndHourDocument.endTime())).toList();

        return Promotion.of(promotionDocument.description(), promotionDocument.promotionalPrice(), daysAndHours);
    }

}
