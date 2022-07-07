package com.br.rank.list.infra.database.documents;

public enum DaysDocument {

    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String day;

    DaysDocument(final String day) {
        this.day = day;
    }

}
