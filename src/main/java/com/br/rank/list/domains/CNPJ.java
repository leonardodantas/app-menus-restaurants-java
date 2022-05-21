package com.br.rank.list.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class CNPJ implements Serializable {

    private String cnpj;
    private String cnpjOnlyNumbers;

    private CNPJ(String cnpj) {
        this.cnpj = cnpj;
        this.cnpjOnlyNumbers = cnpj.replaceAll("\\D", "");
    }

    public static CNPJ from(String cnpj) {
        return new CNPJ(cnpj);
    }

}
