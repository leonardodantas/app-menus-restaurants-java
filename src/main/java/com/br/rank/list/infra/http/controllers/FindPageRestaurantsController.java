package com.br.rank.list.infra.http.controllers;

import com.br.rank.list.app.usecases.IFindPageRestaurants;
import com.br.rank.list.infra.http.jsons.responses.ActiveRestaurantsResponse;
import com.br.rank.list.infra.http.jsons.responses.PageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restaurants")
@Api(tags = "Restaurants")
public class FindPageRestaurantsController {

    private final IFindPageRestaurants findPageRestaurants;

    public FindPageRestaurantsController(final IFindPageRestaurants findPageRestaurants) {
        this.findPageRestaurants = findPageRestaurants;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Restaurants", value = "Find page restaurants")
    public PageResponse<ActiveRestaurantsResponse> execute(@RequestParam(defaultValue = "0") final int page, @RequestParam(defaultValue = "20") final int size) {
        final var response = findPageRestaurants.execute(page, size).map(ActiveRestaurantsResponse::from);
        return new PageResponse<>(response.getContent(), page, size, response.getTotalElements(),response.getTotalPages());
    }
}
