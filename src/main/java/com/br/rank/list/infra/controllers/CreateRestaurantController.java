package com.br.rank.list.infra.controllers;

import com.br.rank.list.app.usecases.ICreateRestaurant;
import com.br.rank.list.infra.converters.RestaurantConverter;
import com.br.rank.list.infra.jsons.requests.RestaurantRequestJson;
import com.br.rank.list.infra.jsons.responses.RestaurantResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
        final var response = RestaurantConverter.toDomain(request);
        this.createRestaurant.execute(response);
        return RestaurantResponse.from(response);
    }
}
