package com.br.rank.list.infra.http.jsons.responses;

import com.br.rank.list.domains.Promotion;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

public record PromotionResponse(
        String description,
        BigDecimal promotionalPrice,
        Collection<DayAndHourResponse> dayAndHours
) {
    public static PromotionResponse from(final Promotion promotion) {

        return new PromotionResponse(
                promotion.getDescription(),
                promotion.getPromotionalPrice(),
                promotion.getDayAndHours().stream().map(DayAndHourResponse::from).collect(Collectors.toList())
        );
    }
}
