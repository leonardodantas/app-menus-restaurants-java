package com.br.rank.list.domains;

import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
public final class Delivery implements Serializable {
    private int minimumDeliveryTime;
    private int maximumDeliveryTime;
    private BigDecimal rate;

    private Delivery(){

    }

    public static Delivery noDelivery(){
        return new Delivery();
    }

    private Delivery(final int minimumDeliveryTime, final int maximumDeliveryTime, final BigDecimal rate) {
        this.minimumDeliveryTime = minimumDeliveryTime;
        this.maximumDeliveryTime = maximumDeliveryTime;
        this.rate = rate;
    }

    public static Delivery of(final int minimumDeliveryTime, final int maximumDeliveryTime, final BigDecimal rate) {
        return new Delivery(minimumDeliveryTime, maximumDeliveryTime, rate);
    }
}
