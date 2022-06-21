package com.br.rank.list.app.usecases.impl;

import com.br.rank.list.app.messages.ISendProductMessage;
import com.br.rank.list.app.usecases.ICreateProductEvents;
import com.br.rank.list.app.usecases.IUpdateProductEvents;
import com.br.rank.list.domains.Product;
import com.br.rank.list.domains.RestaurantCode;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductEvents implements IUpdateProductEvents {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final ISendProductMessage sendProductMessage;

    public UpdateProductEvents(final ApplicationEventPublisher applicationEventPublisher, final ISendProductMessage sendProductMessage) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.sendProductMessage = sendProductMessage;
    }

    @Override
    public void execute(final Product product) {
        applicationEventPublisher.publishEvent(product);
        applicationEventPublisher.publishEvent(RestaurantCode.from(product.getCode()));
        sendProductMessage.execute(product);
    }
}
