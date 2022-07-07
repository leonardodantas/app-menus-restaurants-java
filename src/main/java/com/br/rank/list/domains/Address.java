package com.br.rank.list.domains;

import com.google.common.base.Strings;
import lombok.Getter;

import java.io.Serializable;

@Getter
public final class Address implements Serializable {
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String village;

    private Address(final String street, final String number, final String city, final String state, final String village) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.village = village;
    }

    private Address(final String street, final String number, final String complement, final String city, final String state, final String village) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.village = village;
        this.complement = complement;
    }

    public static Builder builder(final String street, final String number, final String city, final String state, final String village) {
        return new Builder(street, number, city, state, village);
    }

    public static class Builder {

        private final String street;
        private final String number;
        private final String city;
        private final String state;
        private final String village;
        private String complement;

        public Builder(final String street, final String number, final String city, final String state, final String village) {
            this.street = street;
            this.number = number;
            this.city = city;
            this.state = state;
            this.village = village;
        }

        public Builder complement(final String complement) {
            this.complement = complement;
            return this;
        }

        public Address build() {
            if (Strings.isNullOrEmpty(complement)) {
                return new Address(street, number, city, state, village);
            }
            return new Address(street, number, complement, city, state, village);

        }


    }
}
