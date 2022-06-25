package com.br.rank.list.infra.http.jsons.responses;

import com.br.rank.list.domains.Address;

public record AddressResponse(
        String street,
        String number,
        String city,
        String complement,
        String state,
        String village
) {
    public static AddressResponse from(final Address address) {
        return new AddressResponse(address.getStreet(), address.getNumber(), address.getCity(), address.getComplement(), address.getState(), address.getVillage());
    }
}
