package com.br.rank.list.infra.jsons.requests;


import com.br.rank.list.infra.annotations.hours.HoursValid;

public record DayAndHourRequestJson(
        DaysRequest day,
        @HoursValid
        String startTime,
        @HoursValid
        String endTime
) {
}
