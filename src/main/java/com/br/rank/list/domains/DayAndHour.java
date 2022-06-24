package com.br.rank.list.domains;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

@Getter
@Builder
public class DayAndHour implements Serializable {

    private Days day;
    private LocalTime startTime;
    private LocalTime endTime;

    public long getOperatingTime() {
        return Duration.between(startTime, endTime).toMinutes();
    }

}
