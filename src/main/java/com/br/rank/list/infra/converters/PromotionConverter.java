package com.br.rank.list.infra.converters;

import com.br.rank.list.domains.DayAndHour;
import com.br.rank.list.domains.Days;
import com.br.rank.list.domains.Promotion;
import com.br.rank.list.infra.jsons.requests.PromotionRequestJson;

import java.util.Collection;

public class PromotionConverter {

    public static Promotion toDomain(final PromotionRequestJson json) {
        return Promotion.builder()
                .description(json.description())
                .promotionalPrice(json.promotionalPrice())
                .dayAndHours(getDaysAndHours(json))
                .build();
    }

    private static Collection<DayAndHour> getDaysAndHours(final PromotionRequestJson json) {
        return json.daysAndHours().stream()
                .map(d -> DayAndHour
                        .builder()
                        .day(Days.valueOf(d.day().name()))
                        .startTime(HoursConverter.toLocalTime(d.startTime()))
                        .endTime(HoursConverter.toLocalTime(d.endTime()))
                        .build())
                .toList();
    }
}
