package com.br.rank.list.domains;

public enum Days {

    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String day;

    Days(final String day) {
        this.day = day;
    }

    public boolean equalsIgnoreCase(final String day) {
        return this.day.equalsIgnoreCase(day);
    }
}
