package com.br.rank.list.infra.controllers;

import com.br.rank.list.app.usecases.IGetCategories;
import com.br.rank.list.infra.jsons.responses.CategoriesResponseJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categories")
@Api(tags = "Categories")
public class GetCategoriesController {

    private final IGetCategories getCategories;

    public GetCategoriesController(final IGetCategories getCategories) {
        this.getCategories = getCategories;
    }

    @GetMapping("restaurant/{code}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Categories", value = "Find categories by restaurant")
    public CategoriesResponseJson execute(@PathVariable final String code) {
        final var response = getCategories.execute(code);
        return CategoriesResponseJson.from(response);
    }
}
