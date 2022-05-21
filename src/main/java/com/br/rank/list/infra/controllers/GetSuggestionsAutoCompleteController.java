package com.br.rank.list.infra.controllers;


import com.br.rank.list.app.usecases.IGetSuggestionsProducts;
import com.br.rank.list.app.usecases.IGetSuggestionsRestaurants;
import com.br.rank.list.infra.jsons.responses.SearchProductResponse;
import com.br.rank.list.infra.jsons.responses.SearchRestaurantResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Api(tags = "Suggestions")
@RestController
@RequestMapping("suggestions")
public class GetSuggestionsAutoCompleteController {

    private final IGetSuggestionsRestaurants getSuggestionsAutoComplete;
    private final IGetSuggestionsProducts getSuggestionsProducts;

    public GetSuggestionsAutoCompleteController(final IGetSuggestionsRestaurants getSuggestionsAutoComplete, final IGetSuggestionsProducts getSuggestionsProducts) {
        this.getSuggestionsAutoComplete = getSuggestionsAutoComplete;
        this.getSuggestionsProducts = getSuggestionsProducts;
    }

    @GetMapping("/search/{search}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Suggestions", value = "Find suggestions of restaurants")
    public Collection<SearchRestaurantResponse> executeInRestaurants(@PathVariable final String search) {
        final var response = getSuggestionsAutoComplete.execute(search);
        return response.stream().map(SearchRestaurantResponse::from).toList();
    }

    @GetMapping("/search/{search}/restaurant/{code}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Suggestions", value = "Find suggestions products")
    public Collection<SearchProductResponse> executeInProducts(@PathVariable final String search, @PathVariable final String code) {
        final var response = getSuggestionsProducts.execute(code, search);
        return response.stream().map(SearchProductResponse::from).toList();
    }

}
