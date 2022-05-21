package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.Restaurant;
import org.springframework.data.domain.Page;

public interface IFindPageRestaurants {
    Page<Restaurant> execute(int page, int size);
}
