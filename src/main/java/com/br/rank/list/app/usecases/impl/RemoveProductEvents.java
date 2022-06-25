package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.messages.IRemoveProductMessage;
import com.br.rank.list.app.usecases.IRemoveProductEvents;
import com.br.rank.list.domains.RestaurantCode;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class RemoveProductEvents implements IRemoveProductEvents {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final IRemoveProductMessage removeProductMessage;

    public RemoveProductEvents(final ApplicationEventPublisher applicationEventPublisher, final IRemoveProductMessage removeProductMessage) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.removeProductMessage = removeProductMessage;
    }

    @Override
    public void execute(final String id, final String code) {
        applicationEventPublisher.publishEvent(new RestaurantCode(code));
        removeProductMessage.execute(id);
    }
}
