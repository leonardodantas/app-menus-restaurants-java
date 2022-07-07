package com.br.rank.list.infra.http.jsons.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddressRequestJson(
        @NotBlank
        @Size(min = 1, max = 40)
        String street,
        @NotBlank
        @Size(min = 1, max = 6)
        String number,
        @NotBlank
        @Size(min = 1, max = 40)
        String city,
        @Size(max = 40)
        String complement,
        @NotBlank
        @Size(min = 1, max = 3)
        String state,
        @NotBlank
        @Size(min = 1, max = 40)
        String village
) {
}
