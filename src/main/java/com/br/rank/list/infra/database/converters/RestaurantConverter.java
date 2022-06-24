package com.br.rank.list.infra.database.converters;

import com.br.rank.list.domains.*;
import com.br.rank.list.infra.database.documents.AddressDocument;
import com.br.rank.list.infra.database.documents.DeliveryDocument;
import com.br.rank.list.infra.database.documents.OperatingHoursDocument;
import com.br.rank.list.infra.database.documents.RestaurantDocument;

public class RestaurantConverter {

    public static Restaurant toDomain(final RestaurantDocument document) {
        return Restaurant.builder()
                .id(document.id())
                .code(document.code())
                .cnpj(CNPJ.from(document.cnpjValue()))
                .name(document.name())
                .address(toDomain(document.address()))
                .openingHours(toDomain(document.openingHours()))
                .delivery(toDomain(document.delivery()))
                .deliveryAvailable(document.deliveryAvailable())
                .open(document.open())
                .active(document.active())
                .build();
    }

    private static Address toDomain(final AddressDocument document) {
        return Address.builder()
                .street(document.street())
                .number(document.number())
                .state(document.state())
                .city(document.city())
                .village(document.village())
                .complement(document.complement())
                .build();
    }

    private static OperatingHours toDomain(final OperatingHoursDocument document) {
        return OperatingHours.builder()
                .startTime(document.startTime())
                .endTime(document.endTime())
                .build();
    }

    private static Delivery toDomain(final DeliveryDocument document) {
        return Delivery.builder()
                .minimumDeliveryTime(document.minimumDeliveryTime())
                .maximumDeliveryTime(document.maximumDeliveryTime())
                .rate(document.rate())
                .build();
    }
}
