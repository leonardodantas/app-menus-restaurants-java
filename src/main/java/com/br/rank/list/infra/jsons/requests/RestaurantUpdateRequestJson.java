package com.br.rank.list.infra.jsons.requests;

import com.br.rank.list.infra.annotations.hours.OperatingHoursValid;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record RestaurantUpdateRequestJson(
        @Valid @NotNull
        AddressRequestJson address,
        @NotNull @OperatingHoursValid
        OperatingHoursRequestJson operatingHours
) {
}
