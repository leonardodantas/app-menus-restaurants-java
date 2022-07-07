package com.br.rank.list.infra.http.controllers.advice.responses;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;


public record ErrorsResponse(String uuid,
                             LocalDateTime date, Collection<MessageError> errors) {

    public static ErrorsResponse from(final Collection<MessageError> message) {
        return new ErrorsResponse(UUID.randomUUID().toString(), LocalDateTime.now(), message);
    }

}