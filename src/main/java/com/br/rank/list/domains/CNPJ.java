package com.br.rank.list.domains;

import lombok.Getter;

import java.io.Serializable;

@Getter
public final class CNPJ implements Serializable {

    private String value;
    private String onlyNumbers;

    private CNPJ(final String value) {
        this.value = value;
        this.onlyNumbers = value.replaceAll("\\D", "");
    }

    public static CNPJ from(final String cnpj) {
        return new CNPJ(cnpj);
    }

}
