package com.br.rank.list.app.usecases;

import com.br.rank.list.domains.Categories;

public interface IGetCategories {

    Categories execute(String code);
}
