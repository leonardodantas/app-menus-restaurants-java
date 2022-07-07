package com.br.rank.list.infra.http.jsons.responses;

import com.br.rank.list.domains.DayAndHour;

import java.time.LocalTime;

public record DayAndHourResponse(
        DaysResponse day,
        LocalTime startTime,
        LocalTime endTime
) {

    public static DayAndHourResponse from(final DayAndHour dayOrHour) {
        return new DayAndHourResponse(
                DaysResponse.valueOf(dayOrHour.getDay().name()),
                dayOrHour.getStartTime(), dayOrHour.getEndTime());
    }
}
