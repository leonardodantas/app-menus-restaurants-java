package com.br.rank.list.app.exceptions;

public class TimeBetweenHoursException extends RuntimeException {

    private TimeBetweenHoursException(final String message) {
        super(message);
    }

    public static TimeBetweenHoursException from(final String message) {
        return new TimeBetweenHoursException(message);
    }
}
