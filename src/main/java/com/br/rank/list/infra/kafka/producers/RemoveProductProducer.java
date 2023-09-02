package com.br.rank.list.infra.kafka.producers;

import com.br.rank.list.app.messages.IRemoveProductMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RemoveProductProducer implements IRemoveProductMessage {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String REMOVE_TOPIC_KAFKA = "remove.product.meiliSearch";

    public RemoveProductProducer(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void execute(final String productId) {
        kafkaTemplate.send(REMOVE_TOPIC_KAFKA, productId);
    }
}
