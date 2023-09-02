package com.br.rank.list.infra.http.converters;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HoursConverter {

    public static LocalTime toLocalTime(final String date) {
        return LocalTime.parse(date);
    }
}
