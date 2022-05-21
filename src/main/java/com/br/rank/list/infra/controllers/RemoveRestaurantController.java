package com.br.rank.list.infra.controllers;

import com.br.rank.list.app.usecases.IRemoveRestaurant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("restaurant")
@Api(tags = "Restaurants")
public class RemoveRestaurantController {

    private final IRemoveRestaurant removeRestaurant;

    public RemoveRestaurantController(final IRemoveRestaurant removeRestaurant) {
        this.removeRestaurant = removeRestaurant;
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Restaurants", value = "Remove a restaurant by id")
    public void execute(@PathVariable final String id) {
        removeRestaurant.execute(id);
    }
}
