package com.br.rank.list.infra.kafka.consumers;

import com.br.rank.list.app.usecases.IRemoveSearchInformation;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RemoveSearchInformationConsumer {

    private static final String SEARCH_INFORMATION_REMOVE = "remove.search.information";
    private final IRemoveSearchInformation removeSearchInformation;

    public RemoveSearchInformationConsumer(final IRemoveSearchInformation removeSearchInformation) {
        this.removeSearchInformation = removeSearchInformation;
    }

    @KafkaListener(topics = SEARCH_INFORMATION_REMOVE, groupId = "group-searchInformationRemove")
    public void listener(final String productId) {
        removeSearchInformation.execute(productId);
    }
}
