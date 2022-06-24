package com.br.rank.list.infra.database.documents;

public record CNPJDocument(
        String cnpj,
        String cnpjOnlyNumbers
) {

    public static CNPJDocument from(final String cnpj) {
        final var cnpjOnlyNumbers = cnpj.replaceAll("\\D", "");
        return new CNPJDocument(cnpj, cnpjOnlyNumbers);
    }
}
