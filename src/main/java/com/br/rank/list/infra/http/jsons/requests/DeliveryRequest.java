package com.br.rank.list.infra.http.jsons.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record DeliveryRequest(
        @Min(1)
        int minimumDeliveryTime,
        @Min(1)
        int maximumDeliveryTime,
        @NotNull
        BigDecimal rate
) {
}
