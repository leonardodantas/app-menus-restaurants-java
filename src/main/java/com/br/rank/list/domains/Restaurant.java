package com.br.rank.list.domains;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

@Builder
@Getter
@Document("restaurant")
public class Restaurant implements Serializable {

    @Id
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

    public void updateFrom(Restaurant restaurant) {
        this.address = restaurant.address;
        this.openingHours = restaurant.openingHours;
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

    public long getOperatingTime(){
        return Duration.between(getOpeningHours().getStartTime(), getOpeningHours().getEndTime()).toMinutes();
    }

}
