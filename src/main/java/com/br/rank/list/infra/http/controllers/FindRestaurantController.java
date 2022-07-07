package com.br.rank.list.infra.http.controllers;

import com.br.rank.list.app.usecases.IFindRestaurant;
import com.br.rank.list.domains.Restaurant;
import com.br.rank.list.infra.http.jsons.responses.RestaurantDetailsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("restaurant")
@RestController
@Api(tags = "Restaurants")
public class FindRestaurantController {

    private final IFindRestaurant findRestaurant;

    public FindRestaurantController(final IFindRestaurant findRestaurant) {
        this.findRestaurant = findRestaurant;
    }

    @GetMapping("id/{id}/details")
    @ApiOperation(tags = "Restaurants", value = "Find restaurant by id")
    public ResponseEntity<RestaurantDetailsResponse> findById(@PathVariable final String id) {
        final var response = findRestaurant.findById(id);
        return this.getResponse(response);
    }

    @GetMapping("cnpj/{cnpj}/details")
    @ApiOperation(tags = "Restaurants", value = "Find restaurant by cnpj")
    public ResponseEntity<RestaurantDetailsResponse> findByCNPJ(@PathVariable final String cnpj) {
        final var response = findRestaurant.findByCnpj(cnpj);
        return this.getResponse(response);
    }

    @GetMapping("code/{code}/details")
    @ApiOperation(tags = "Restaurants", value = "Find restaurant by code")
    public ResponseEntity<RestaurantDetailsResponse> findByCode(@PathVariable final String code) {
        final var response = findRestaurant.findByCode(code);
        return this.getResponse(response);
    }

    private ResponseEntity<RestaurantDetailsResponse> getResponse(Optional<Restaurant> response) {
        return response
                .map(restaurant -> ResponseEntity.ok(RestaurantDetailsResponse.from(restaurant)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
