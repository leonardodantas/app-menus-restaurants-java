package com.br.rank.list.infra.kafka;

import com.br.rank.list.app.messages.ISendProductMessage;
import com.br.rank.list.domains.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendProductMessage implements ISendProductMessage {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final static String SEND_TOPIC_KAFKA = "sendProduct";

    public SendProductMessage(final KafkaTemplate<String, String> kafkaTemplate, final ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(final Product product) {
        try {
            final var message = objectMapper.writeValueAsString(product);
            kafkaTemplate.send(SEND_TOPIC_KAFKA, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
