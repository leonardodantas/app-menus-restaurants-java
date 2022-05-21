package com.br.rank.list.domains;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class Address implements Serializable {
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String village;
}
