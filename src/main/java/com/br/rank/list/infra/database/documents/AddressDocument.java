package com.br.rank.list.infra.database.documents;

import com.br.rank.list.domains.Address;

public record AddressDocument(
        String street,
        String number,
        String complement,
        String city,
        String state,
        String village
) {

    public static AddressDocument from(final Address address) {
        return new AddressDocument(address.getStreet(), address.getNumber(), address.getComplement(), address.getCity(), address.getState(), address.getVillage());
    }
}
