package com.br.rank.list.infra.database.documents;

import com.br.rank.list.domains.DayAndHour;

import java.time.LocalTime;

public record DayAndHourDocument(
        DaysDocument day,
        LocalTime startTime,
        LocalTime endTime
) {

    public static DayAndHourDocument from(final DayAndHour dayAndHour) {
        return new DayAndHourDocument(DaysDocument.valueOf(dayAndHour.getDay().name()), dayAndHour.getStartTime(), dayAndHour.getEndTime());
    }


}
