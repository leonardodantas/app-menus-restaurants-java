package com.br.rank.list.infra.http.jsons.responses;

public enum DaysResponse {

    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String day;

    DaysResponse(final String day) {
        this.day = day;
    }
}
