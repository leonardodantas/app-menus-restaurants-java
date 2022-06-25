package com.br.rank.list.infra.http.controllers;

import com.br.rank.list.app.usecases.IFindByCategories;
import com.br.rank.list.domains.Categories;
import com.br.rank.list.infra.http.jsons.requests.CategoriesRequestJson;
import com.br.rank.list.infra.http.jsons.responses.ProductResponseJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequestMapping("categories")
@RestController
@Api(tags = "Categories")
public class FindByCategoriesController {

    private final IFindByCategories findByCategories;

    public FindByCategoriesController(final IFindByCategories findByCategories) {
        this.findByCategories = findByCategories;
    }

    @PostMapping("restaurants/{code}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Categories", value = "Find products by categories")
    public Collection<ProductResponseJson> execute(@PathVariable final String code, @RequestBody final CategoriesRequestJson request){
        final var response = findByCategories.execute(code, Categories.from(request.values()));
        return response.stream().map(ProductResponseJson::from).toList();
    }
}
