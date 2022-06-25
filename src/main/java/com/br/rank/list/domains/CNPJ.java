package com.br.rank.list.domains;

import lombok.Getter;

import java.io.Serializable;

@Getter
public final class CNPJ implements Serializable {

    private String cnpj;
    private String cnpjOnlyNumbers;

    private CNPJ(final String cnpj) {
        this.cnpj = cnpj;
        this.cnpjOnlyNumbers = cnpj.replaceAll("\\D", "");
    }

    public static CNPJ from(final String cnpj) {
        return new CNPJ(cnpj);
    }

}
