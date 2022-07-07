package com.br.rank.list.infra.database.documents;

import com.br.rank.list.domains.OperatingHours;

import java.time.LocalTime;

public record OperatingHoursDocument(
        LocalTime startTime,
        LocalTime endTime
) {

    public static OperatingHoursDocument from(final OperatingHours operatingHours) {
        return new OperatingHoursDocument(operatingHours.getStartTime(), operatingHours.getEndTime());
    }
}
