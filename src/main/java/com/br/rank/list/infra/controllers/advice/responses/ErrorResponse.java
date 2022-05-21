package com.br.rank.list.infra.controllers.advice.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record ErrorResponse(
        String uuid,
        String message,
        LocalDateTime timestamp) {

    public static ErrorResponse from(final RuntimeException restaurantNotFoundException) {
        return new ErrorResponse(UUID.randomUUID().toString(), restaurantNotFoundException.getMessage(), LocalDateTime.now());
    }
}
