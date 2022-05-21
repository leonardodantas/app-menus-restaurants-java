package com.br.rank.list.infra.jsons.requests;

import com.br.rank.list.infra.annotations.hours.HoursValid;

public record OperatingHoursRequestJson(
        @HoursValid
        String startTime,
        @HoursValid
        String endTime
) {
}
