package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.Delivery;

public interface IActivateDeactivateDelivery {
    Delivery activate(String code, Delivery delivery);

    void deactivate(String code);
}
