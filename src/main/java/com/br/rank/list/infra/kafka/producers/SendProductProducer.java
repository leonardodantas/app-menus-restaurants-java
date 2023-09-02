package com.br.rank.list.infra.kafka.producers;

import com.br.rank.list.app.messages.ISendProductMessage;
import com.br.rank.list.domains.Product;
import com.br.rank.list.infra.exceptions.ProducerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendProductProducer implements ISendProductMessage {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final String SEND_TOPIC_KAFKA = "send.product.meiliSearch";

    public SendProductProducer(final KafkaTemplate<String, String> kafkaTemplate, final ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(final Product product) {
        try {
            final var message = objectMapper.writeValueAsString(product);
            kafkaTemplate.send(SEND_TOPIC_KAFKA, message);
        } catch (final Exception e) {
            throw new ProducerException(e);
        }
    }
}
