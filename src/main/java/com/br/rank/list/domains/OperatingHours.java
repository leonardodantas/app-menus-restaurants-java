package com.br.rank.list.domains;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
public final class OperatingHours implements Serializable {
    private LocalTime startTime;
    private LocalTime endTime;

    private OperatingHours(final LocalTime startTime, final LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static OperatingHours of(final LocalTime startTime, final LocalTime endTime) {
        return new OperatingHours(startTime, endTime);
    }
}
