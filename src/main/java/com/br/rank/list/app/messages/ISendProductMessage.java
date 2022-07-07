package com.br.rank.list.app.messages;

import com.br.rank.list.domains.Product;

public interface ISendProductMessage {

    void execute(Product product);
}
