package com.br.rank.list.infra.converters;

import java.time.LocalTime;

public class HoursConverter {

    public static LocalTime toLocalTime(final String date) {
        return LocalTime.parse(date);
    }
}
