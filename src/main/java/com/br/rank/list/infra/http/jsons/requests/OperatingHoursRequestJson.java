package com.br.rank.list.infra.http.jsons.requests;

import com.br.rank.list.infra.http.annotations.hours.HoursValid;

public record OperatingHoursRequestJson(
        @HoursValid
        String startTime,
        @HoursValid
        String endTime
) {
}
