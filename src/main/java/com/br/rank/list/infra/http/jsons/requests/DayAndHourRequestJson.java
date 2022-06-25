package com.br.rank.list.infra.http.jsons.requests;


import com.br.rank.list.infra.http.annotations.hours.HoursValid;

public record DayAndHourRequestJson(
        DaysRequest day,
        @HoursValid
        String startTime,
        @HoursValid
        String endTime
) {
}
