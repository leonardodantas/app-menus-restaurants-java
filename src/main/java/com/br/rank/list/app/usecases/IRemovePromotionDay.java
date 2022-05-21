package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.Days;

public interface IRemovePromotionDay {
    void execute(String productId, Days day);
}
