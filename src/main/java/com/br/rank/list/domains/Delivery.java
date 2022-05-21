package com.br.rank.list.domains;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Builder
public class Delivery implements Serializable {

    private int minimumDeliveryTime;
    private int maximumDeliveryTime;
    private BigDecimal rate;
}
