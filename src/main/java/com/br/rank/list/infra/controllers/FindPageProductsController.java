package com.br.rank.list.infra.controllers;

import com.br.rank.list.app.usecases.IFindProducts;
import com.br.rank.list.infra.jsons.responses.PageResponse;
import com.br.rank.list.infra.jsons.responses.ProductResponseJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
@Api(tags = "Products")
public class FindPageProductsController {

    private final IFindProducts findProducts;

    public FindPageProductsController(final IFindProducts findProducts) {
        this.findProducts = findProducts;
    }

    @GetMapping("restaurant/{code}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Products", value = "Find products who do not have promotion by restaurant")
    public PageResponse<ProductResponseJson> execute(@PathVariable final String code,
                                                     @RequestParam(defaultValue = "0") final int page,
                                                     @RequestParam(defaultValue = "20") final int size) {
        final var response = findProducts.execute(code, page, size).map(ProductResponseJson::from);
        return new PageResponse<ProductResponseJson>(response.getContent(), page, size, response.getTotalElements(), response.getTotalPages());
    }
}
