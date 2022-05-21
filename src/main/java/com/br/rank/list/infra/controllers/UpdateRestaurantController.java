package com.br.rank.list.infra.controllers;

import com.br.rank.list.app.usecases.IUpdateRestaurant;
import com.br.rank.list.infra.converters.RestaurantConverter;
import com.br.rank.list.infra.jsons.requests.RestaurantUpdateRequestJson;
import com.br.rank.list.infra.jsons.responses.RestaurantResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("restaurant")
@Api(tags = "Restaurants")
public class UpdateRestaurantController {

    private final IUpdateRestaurant updateRestaurant;

    public UpdateRestaurantController(final IUpdateRestaurant updateRestaurant) {
        this.updateRestaurant = updateRestaurant;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Restaurants", value = "Update Restaurant")
    public RestaurantResponse execute(@RequestParam final String id, @Valid @RequestBody final RestaurantUpdateRequestJson request) {
        final var response = updateRestaurant.execute(id, RestaurantConverter.toDomain(request));
        return RestaurantResponse.from(response);
    }
}
