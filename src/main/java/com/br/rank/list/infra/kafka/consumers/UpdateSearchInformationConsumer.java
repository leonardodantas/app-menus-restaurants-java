package com.br.rank.list.infra.kafka.consumers;

import com.br.rank.list.app.usecases.IUpdateSearchInformation;
import com.br.rank.list.domains.SearchInformation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UpdateSearchInformationConsumer {

    private static final String SEARCH_INFORMATION_UPDATE = "send.search.information";
    private final ObjectMapper objectMapper;
    private final IUpdateSearchInformation searchInformation;

    public UpdateSearchInformationConsumer(final ObjectMapper objectMapper, final IUpdateSearchInformation searchInformation) {
        this.objectMapper = objectMapper;
        this.searchInformation = searchInformation;
    }

    @KafkaListener(topics = SEARCH_INFORMATION_UPDATE, groupId = "group-searchInformationUpdate")
    public void listener(final String message) {
        try {
            final var search = objectMapper.readValue(message, SearchInformation.class);
            searchInformation.execute(search);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
