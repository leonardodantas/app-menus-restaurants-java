package com.br.rank.list.domains;

import com.br.rank.list.domains.exceptions.TimeBetweenHoursException;
import lombok.Getter;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Locale;
import java.util.UUID;

@Getter
public final class Restaurant implements Serializable {

    private String id;
    private String code;
    private CNPJ cnpj;
    private String name;
    private Address address;
    private OperatingHours openingHours;
    private Delivery delivery;
    private boolean deliveryAvailable;
    private boolean open;
    private boolean active;

    private Restaurant(final String id, final String code, final CNPJ cnpj, final String name, final Address address, final OperatingHours openingHours, final Delivery delivery, final boolean deliveryAvailable, final boolean open, final boolean active) {
        this.id = id;
        this.code = code;
        this.cnpj = cnpj;
        this.name = name;
        this.address = address;
        this.openingHours = openingHours;
        this.delivery = delivery;
        this.deliveryAvailable = deliveryAvailable;
        this.open = open;
        this.active = active;

        this.validateOperationTime();
    }

    public static Restaurant activeDeliveryOf(final Restaurant restaurant, final Delivery delivery) {
        return new Restaurant(restaurant.getId(), restaurant.getCode(), CNPJ.from(restaurant.getCnpj()), restaurant.getName(), restaurant.getAddress(), restaurant.getOpeningHours(), delivery, true, restaurant.isOpen(), restaurant.isActive());
    }

    public LocalTime getStartTime() {
        return openingHours.getStartTime();
    }

    public LocalTime getEndTime() {
        return openingHours.getEndTime();
    }

    public boolean isClosed() {
        return !this.isOpen();
    }

    public String getCnpj() {
        return cnpj.getCnpj();
    }

    public void disable() {
        this.active = false;
    }

    public void updateFrom(final Restaurant restaurant) {
        this.name = restaurant.getName();
        this.cnpj = CNPJ.from(restaurant.getCnpj());
        this.address = restaurant.getAddress();
        this.openingHours = restaurant.getOpeningHours();
    }

    public Restaurant open() {
        this.open = true;
        return this;
    }

    public Restaurant close() {
        this.open = false;
        return this;
    }

    public Restaurant disableDelivery() {
        this.deliveryAvailable = false;
        return this;
    }

    private long getOperatingTime() {
        return Duration.between(getOpeningHours().getStartTime(), getOpeningHours().getEndTime()).toMinutes();
    }

    private void validateOperationTime() {
        if (this.getOperatingTime() < 15) {
            throw new TimeBetweenHoursException("Operation time restaurant " + this.name + " is less than 15 minutes");
        }
    }

    public static Builder builder(final CNPJ cnpj, final String name, final Address address, final OperatingHours openingHours) {
        return new Builder(cnpj, name, address, openingHours);
    }

    public static class Builder {
        private final CNPJ cnpj;
        private final String name;
        private final Address address;
        private final OperatingHours openingHours;

        private String id;
        private String code;
        private Delivery delivery;
        private boolean deliveryAvailable;
        private boolean open;
        private boolean active;

        public Builder(final CNPJ cnpj, final String name, final Address address, final OperatingHours openingHours) {
            this.cnpj = cnpj;
            this.name = name;
            this.address = address;
            this.openingHours = openingHours;

            this.code = generateCode(name);
            this.active = true;
            this.delivery = Delivery.noDelivery();
        }

        public Builder id(final String id) {
            this.id = id;
            return this;
        }


        public Builder code(final String code) {
            this.code = code;
            return this;
        }

        public Builder delivery(final Delivery delivery) {
            this.delivery = delivery;
            return this;
        }


        public Builder deliveryAvailable(final boolean deliveryAvailable) {
            this.deliveryAvailable = deliveryAvailable;
            return this;
        }

        public Builder open(final boolean open) {
            this.open = open;
            return this;
        }

        public Builder active(final boolean active) {
            this.active = active;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(id, code, cnpj, name, address, openingHours, delivery, deliveryAvailable, open, active);
        }

        private static String generateCode(final String restaurantName) {

            final var initialCode = restaurantName.substring(0, 7);
            final var finalCode = UUID.randomUUID().toString().substring(0, 5);

            return String.format(initialCode + "-" + finalCode).toUpperCase(Locale.ROOT);
        }
    }

}
