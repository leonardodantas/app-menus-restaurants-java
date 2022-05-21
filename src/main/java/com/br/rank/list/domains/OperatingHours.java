package com.br.rank.list.domains;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalTime;

@Builder
@Getter
public class OperatingHours implements Serializable {

    private LocalTime startTime;
    private LocalTime endTime;
}
