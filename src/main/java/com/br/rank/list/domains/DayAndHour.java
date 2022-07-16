package com.br.rank.list.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public final class DayAndHour implements Serializable {

    private Days day;
    private LocalTime startTime;
    private LocalTime endTime;

    private DayAndHour(final Days day, final LocalTime startTime, final LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getOperatingTime() {
        return Duration.between(startTime, endTime).toMinutes();
    }

    public static DayAndHour of(final Days day, final LocalTime startTime, final LocalTime endTime) {
        return new DayAndHour(day, startTime, endTime);
    }

}
