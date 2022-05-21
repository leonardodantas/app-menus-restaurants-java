package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.Restaurant;

public interface IUpdateRestaurant {

    Restaurant execute(String id, Restaurant restaurant);
}
