package com.br.rank.list.app.validators;

import com.br.rank.list.app.exceptions.TimeBetweenHoursException;
import com.br.rank.list.domains.Promotion;
import com.br.rank.list.domains.Restaurant;

public interface IValidateTimeBetweenHours {

    static void valid(final Restaurant restaurant) {
        if (restaurant.getOperatingTime() < 15) {
            throw TimeBetweenHoursException.from("Operation time restaurant " + restaurant.getName() + " is less than 15 minutes");
        }
    }

    static void valid(final Promotion promotion) {
        final var invalid = promotion.getDayAndHours().stream().anyMatch(
                dayAndHour -> dayAndHour.getOperatingTime() < 15
        );

        if (invalid) {
            throw TimeBetweenHoursException.from("Operation time promotion " + promotion.getDescription() + " is less than 15 minutes");
        }
    }

}
