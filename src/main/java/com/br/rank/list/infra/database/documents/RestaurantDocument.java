package com.br.rank.list.infra.database.documents;

import com.br.rank.list.domains.Restaurant;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("restaurant")
public record RestaurantDocument(
        String id,
        String code,
        CNPJDocument cnpj,
        String name,
        AddressDocument address,
        OperatingHoursDocument openingHours,
        DeliveryDocument delivery,
        boolean deliveryAvailable,
        boolean open,
        boolean active
) {

    public static RestaurantDocument from(final Restaurant restaurant) {
        return new RestaurantDocument(restaurant.getId(), restaurant.getCode(), CNPJDocument.from(restaurant.getCnpj()), restaurant.getName(),
                AddressDocument.from(restaurant.getAddress()), OperatingHoursDocument.from(restaurant.getOpeningHours()),
                DeliveryDocument.from(restaurant.getDelivery()), restaurant.isDeliveryAvailable(), restaurant.isOpen(), restaurant.isActive()
        );
    }

    public String cnpjValue() {
        return cnpj.cnpj();
    }


}
