package com.br.rank.list.infra.http.controllers;

import com.br.rank.list.app.usecases.ICreateRestaurant;
import com.br.rank.list.infra.http.converters.RestaurantConverter;
import com.br.rank.list.infra.http.jsons.requests.RestaurantRequestJson;
import com.br.rank.list.infra.http.jsons.responses.RestaurantResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("restaurant")
@Api(tags = "Restaurants")
public class CreateRestaurantController {

    private final ICreateRestaurant createRestaurant;

    public CreateRestaurantController(final ICreateRestaurant createRestaurant) {
        this.createRestaurant = createRestaurant;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(tags = "Restaurants", value = "Create a restaurant")
    public RestaurantResponse execute(@Valid @RequestBody final RestaurantRequestJson request) {
        final var response = this.createRestaurant.execute(RestaurantConverter.toDomain(request));
        return RestaurantResponse.from(response);
    }
}
