package com.br.rank.list.infra.database.converters;

import com.br.rank.list.domains.*;
import com.br.rank.list.infra.database.documents.ProductDocument;
import com.br.rank.list.infra.database.documents.PromotionDocument;
import com.br.rank.list.infra.database.documents.SearchInformationDocument;

public class ProductConverter {

    public static Product toDomain(final ProductDocument productDocument) {
        return Product.builder()
                .id(productDocument.id())
                .code(productDocument.code())
                .name(productDocument.name())
                .price(productDocument.price())
                .categories(Categories.from(productDocument.categoriesValues()))
                .promotionActive(productDocument.promotionActive())
                .promotion(toDomain(productDocument.promotion()))
                .searchInformation(toDomain(productDocument.searchInformation()))
                .build();
    }

    private static Promotion toDomain(final PromotionDocument promotionDocument) {

        final var daysAndHours = promotionDocument.dayAndHours().stream().map(dayAndHourDocument -> DayAndHour.builder()
                .day(Days.valueOf(dayAndHourDocument.day().name()))
                .startTime(dayAndHourDocument.startTime())
                .endTime(dayAndHourDocument.endTime())
                .build()).toList();

        return Promotion.builder()
                .description(promotionDocument.description())
                .promotionalPrice(promotionDocument.promotionalPrice())
                .dayAndHours(daysAndHours)
                .build();
    }

    private static SearchInformation toDomain(final SearchInformationDocument searchInformationDocument) {
        return SearchInformation.builder()
                .productId(searchInformationDocument.productId())
                .createAt(searchInformationDocument.createAt())
                .updateAt(searchInformationDocument.updateAt())
                .nameSearch(searchInformationDocument.nameSearch())
                .build();
    }
}
