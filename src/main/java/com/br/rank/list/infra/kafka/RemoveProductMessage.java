package com.br.rank.list.infra.kafka;

import com.br.rank.list.app.messages.IRemoveProductMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RemoveProductMessage implements IRemoveProductMessage {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final static String REMOVE_TOPIC_KAFKA = "removeProduct";

    public RemoveProductMessage(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void execute(final String productId) {
        kafkaTemplate.send(REMOVE_TOPIC_KAFKA, productId);
    }
}
