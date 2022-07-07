package com.br.rank.list.infra.http.converters;

import com.br.rank.list.domains.Address;
import com.br.rank.list.domains.CNPJ;
import com.br.rank.list.domains.OperatingHours;
import com.br.rank.list.domains.Restaurant;
import com.br.rank.list.infra.http.jsons.requests.AddressRequestJson;
import com.br.rank.list.infra.http.jsons.requests.OperatingHoursRequestJson;
import com.br.rank.list.infra.http.jsons.requests.RestaurantRequestJson;
import com.br.rank.list.infra.http.jsons.requests.RestaurantUpdateRequestJson;

public class RestaurantConverter {

    public static Restaurant toDomain(final RestaurantRequestJson json) {
        return Restaurant.builder(CNPJ.from(json.cnpj()), json.name(), toDomain(json.address()), toDomain(json.operatingHours()))
                .build();

    }

    private static Address toDomain(final AddressRequestJson json) {
        return Address.builder(json.street(), json.number(), json.state(),
                        json.city(), json.village())
                .complement(json.complement())
                .build();
    }

    private static OperatingHours toDomain(final OperatingHoursRequestJson json) {
        return OperatingHours.of(HoursConverter.toLocalTime(json.startTime()), HoursConverter.toLocalTime(json.endTime()));
    }

    public static Restaurant toDomain(final RestaurantUpdateRequestJson json) {
        return Restaurant.builder(CNPJ.from(json.cnpj()), json.name(), toDomain(json.address()), toDomain(json.operatingHours()))
                .build();
    }
}
