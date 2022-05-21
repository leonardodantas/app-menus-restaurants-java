package com.br.rank.list.infra.jsons.requests;

public enum DaysRequest {

    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String day;

    DaysRequest(final String day) {
        this.day = day;
    }
}
