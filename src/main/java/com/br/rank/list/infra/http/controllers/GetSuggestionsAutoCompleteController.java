package com.br.rank.list.infra.http.controllers;


import com.br.rank.list.app.usecases.IGetSuggestionsProducts;
import com.br.rank.list.app.usecases.IGetSuggestionsProductsAllRestaurants;
import com.br.rank.list.app.usecases.IGetSuggestionsRestaurants;
import com.br.rank.list.infra.http.jsons.responses.SearchProductResponse;
import com.br.rank.list.infra.http.jsons.responses.SearchRestaurantResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Api(tags = "Suggestions")
@RestController
@RequestMapping("suggestions")
public class GetSuggestionsAutoCompleteController {

    private final IGetSuggestionsRestaurants getSuggestionsAutoComplete;
    private final IGetSuggestionsProducts getSuggestionsProducts;

    private final IGetSuggestionsProductsAllRestaurants getSuggestionsProductsAllRestaurants;

    public GetSuggestionsAutoCompleteController(final IGetSuggestionsRestaurants getSuggestionsAutoComplete, final IGetSuggestionsProducts getSuggestionsProducts,
                                                final IGetSuggestionsProductsAllRestaurants getSuggestionsProductsAllRestaurants) {
        this.getSuggestionsAutoComplete = getSuggestionsAutoComplete;
        this.getSuggestionsProducts = getSuggestionsProducts;
        this.getSuggestionsProductsAllRestaurants = getSuggestionsProductsAllRestaurants;
    }

    @GetMapping("/search/restaurants")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Suggestions", value = "Find suggestions of restaurants")
    public Collection<SearchRestaurantResponse> findRestaurants(@RequestParam final String search) {
        final var response = getSuggestionsAutoComplete.execute(search);
        return response.stream().map(SearchRestaurantResponse::from).toList();
    }

    @GetMapping("/search/{search}/restaurant/{code}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Suggestions", value = "Find suggestions products")
    public Collection<SearchProductResponse> findProductsByRestaurants(@PathVariable final String search, @PathVariable final String code) {
        final var response = getSuggestionsProducts.execute(code, search);
        return response.stream().map(SearchProductResponse::from).toList();
    }

    @GetMapping("/search/products")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Suggestions", value = "Find suggestions of products in all restaurants")
    public Collection<SearchProductResponse> findAProductsInAllRestaurants(@RequestParam final String search) {
        final var response = getSuggestionsProductsAllRestaurants.execute(search);
        return response.stream().map(SearchProductResponse::from).toList();
    }

}
