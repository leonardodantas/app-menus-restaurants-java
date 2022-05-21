package com.br.rank.list.app.usecases;

import com.br.rank.list.app.usecases.impl.OpenCloseRestaurant;
import com.br.rank.list.domains.Restaurant;

public interface IOpenAndCloseRestaurant {
    Restaurant execute(final String id, OpenCloseRestaurant openCloseRestaurant);
}
