package com.br.rank.list.app.validators.impl;

import com.br.rank.list.app.validators.IHoursValid;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class HoursValid implements IHoursValid {

    @Override
    public boolean isValid(final String date) {
        final var dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            dateTimeFormatter.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
