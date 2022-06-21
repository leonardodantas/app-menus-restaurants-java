package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.messages.ISendProductMessage;
import com.br.rank.list.app.usecases.ICreateProductEvents;
import com.br.rank.list.domains.Product;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CreateProductEvents implements ICreateProductEvents {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final ISendProductMessage sendProductMessage;

    public CreateProductEvents(final ApplicationEventPublisher applicationEventPublisher, final ISendProductMessage sendProductMessage) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.sendProductMessage = sendProductMessage;
    }

    @Override
    public void execute(final Product product) {
        applicationEventPublisher.publishEvent(product);
        sendProductMessage.execute(product);
    }
}
