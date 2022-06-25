package com.br.rank.list.infra.http.jsons.requests;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;

public record PromotionRequestJson(
        @NotBlank
        String description,
        @Min(1)
        BigDecimal promotionalPrice,
        @Valid @NotNull
        Collection<DayAndHourRequestJson> daysAndHours
) {
}
