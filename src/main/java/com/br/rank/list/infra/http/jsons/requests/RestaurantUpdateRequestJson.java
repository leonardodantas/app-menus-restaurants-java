package com.br.rank.list.infra.http.jsons.requests;

import com.br.rank.list.infra.http.annotations.hours.OperatingHoursValid;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record RestaurantUpdateRequestJson(
        @CNPJ
        String cnpj,
        @NotBlank @Size(min = 10, max = 120)
        String name,
        @Valid @NotNull
        AddressRequestJson address,
        @NotNull @OperatingHoursValid
        OperatingHoursRequestJson operatingHours
) {
}
