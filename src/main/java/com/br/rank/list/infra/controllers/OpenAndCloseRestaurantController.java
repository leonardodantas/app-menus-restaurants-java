package com.br.rank.list.infra.controllers;

import com.br.rank.list.app.usecases.IOpenAndCloseRestaurant;
import com.br.rank.list.app.usecases.impl.OpenCloseRestaurant;
import com.br.rank.list.infra.jsons.responses.RestaurantResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("restaurant")
@RestController
@Api(tags = "Restaurants")
public class OpenAndCloseRestaurantController {

    private final IOpenAndCloseRestaurant openAndCloseRestaurant;

    public OpenAndCloseRestaurantController(final IOpenAndCloseRestaurant openAndCloseRestaurant) {
        this.openAndCloseRestaurant = openAndCloseRestaurant;
    }

    @PutMapping("open")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Restaurants", value = "Open a restaurant")
    public RestaurantResponse openRestaurant(@RequestParam final String id) {
        final var response = openAndCloseRestaurant.execute(id, OpenCloseRestaurant.OPEN);
        return RestaurantResponse.from(response);
    }

    @PutMapping("close")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Restaurants", value = "Close a restaurant")
    public RestaurantResponse closeRestaurant(@RequestParam final String id) {
        final var response = openAndCloseRestaurant.execute(id, OpenCloseRestaurant.CLOSE);
        return RestaurantResponse.from(response);
    }
}
