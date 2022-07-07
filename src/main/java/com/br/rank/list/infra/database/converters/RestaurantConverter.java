package com.br.rank.list.infra.database.converters;

import com.br.rank.list.domains.*;
import com.br.rank.list.infra.database.documents.AddressDocument;
import com.br.rank.list.infra.database.documents.DeliveryDocument;
import com.br.rank.list.infra.database.documents.OperatingHoursDocument;
import com.br.rank.list.infra.database.documents.RestaurantDocument;

public class RestaurantConverter {

    public static Restaurant toDomain(final RestaurantDocument document) {
        return Restaurant.builder(CNPJ.from(document.cnpjValue()), document.name(), toDomain(document.address()), toDomain(document.openingHours()))
                .id(document.id())
                .code(document.code())
                .delivery(toDomain(document.delivery()))
                .deliveryAvailable(document.deliveryAvailable())
                .open(document.open())
                .active(document.active())
                .build();
    }

    private static Address toDomain(final AddressDocument document) {
        return Address.builder(document.street(), document.number(), document.state(),
                        document.city(), document.village())
                .complement(document.complement())
                .build();
    }

    private static OperatingHours toDomain(final OperatingHoursDocument document) {
        return OperatingHours.of(document.startTime(), document.endTime());
    }

    private static Delivery toDomain(final DeliveryDocument document) {
        return Delivery.of(document.minimumDeliveryTime(), document.maximumDeliveryTime(), document.rate());
    }
}
