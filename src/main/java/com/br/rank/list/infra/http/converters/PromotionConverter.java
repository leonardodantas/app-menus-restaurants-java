package com.br.rank.list.infra.http.converters;

import com.br.rank.list.domains.DayAndHour;
import com.br.rank.list.domains.Days;
import com.br.rank.list.domains.Promotion;
import com.br.rank.list.infra.http.jsons.requests.PromotionRequestJson;

import java.util.Collection;

public class PromotionConverter {

    public static Promotion toDomain(final PromotionRequestJson json) {
        return Promotion.of(json.description(), json.promotionalPrice(), getDaysAndHours(json));
    }

    private static Collection<DayAndHour> getDaysAndHours(final PromotionRequestJson json) {
        return json.daysAndHours().stream()
                .map(d -> DayAndHour.of(Days.valueOf(d.day().name()),
                        HoursConverter.toLocalTime(d.startTime()),
                        HoursConverter.toLocalTime(d.endTime()))).toList();
    }
}
