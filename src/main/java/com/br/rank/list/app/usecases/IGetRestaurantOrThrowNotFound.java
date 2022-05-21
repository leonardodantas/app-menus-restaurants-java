package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.Restaurant;

public interface IGetRestaurantOrThrowNotFound {

    Restaurant execute(String code);
}
