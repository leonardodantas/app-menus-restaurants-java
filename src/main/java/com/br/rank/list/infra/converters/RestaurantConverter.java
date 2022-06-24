package com.br.rank.list.infra.converters;

import com.br.rank.list.domains.*;
import com.br.rank.list.infra.jsons.requests.AddressRequestJson;
import com.br.rank.list.infra.jsons.requests.OperatingHoursRequestJson;
import com.br.rank.list.infra.jsons.requests.RestaurantRequestJson;
import com.br.rank.list.infra.jsons.requests.RestaurantUpdateRequestJson;

import java.util.Locale;
import java.util.UUID;

public class RestaurantConverter {

    public static Restaurant toDomain(final RestaurantRequestJson json) {
        return Restaurant.builder()
                .code(generateCode(json))
                .cnpj(CNPJ.from(json.cnpj()))
                .name(json.name())
                .address(toDomain(json.address()))
                .delivery(Delivery.builder().build())
                .openingHours(toDomain(json.operatingHours()))
                .active(true)
                .build();
    }

    private static String generateCode(final RestaurantRequestJson restaurantRequestJson) {

        final var initialCode = restaurantRequestJson.name().substring(0,7);
        final var finalCode = UUID.randomUUID().toString().substring(0,5);

        return String.format(initialCode + "-" + finalCode).toUpperCase(Locale.ROOT);
    }

    private static Address toDomain(final AddressRequestJson json) {
        return Address.builder()
                .street(json.street())
                .number(json.number())
                .city(json.city())
                .state(json.state())
                .complement(json.complement())
                .village(json.village())
                .build();
    }

    private static OperatingHours toDomain(final OperatingHoursRequestJson json) {
        return OperatingHours.builder()
                .startTime(HoursConverter.toLocalTime(json.startTime()))
                .endTime(HoursConverter.toLocalTime(json.endTime()))
                .build();
    }

    public static Restaurant toDomain(final RestaurantUpdateRequestJson json) {
        return Restaurant.builder()
                .address(toDomain(json.address()))
                .openingHours(toDomain(json.operatingHours()))
                .build();
    }
}
