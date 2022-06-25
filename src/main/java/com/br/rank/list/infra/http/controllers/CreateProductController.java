package com.br.rank.list.infra.http.controllers;

import com.br.rank.list.app.usecases.ICreateProduct;
import com.br.rank.list.infra.http.converters.ProductConverter;
import com.br.rank.list.infra.http.jsons.requests.ProductRequestJson;
import com.br.rank.list.infra.http.jsons.responses.ProductResponseJson;
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
@RequestMapping("product")
@Api(tags = "Products")
public class CreateProductController {

    private final ICreateProduct createProduct;

    public CreateProductController(final ICreateProduct createProduct) {
        this.createProduct = createProduct;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(tags = "Products", value = "Create a new product")
    public ProductResponseJson execute(@Valid @RequestBody final ProductRequestJson request) {
        final var response = createProduct.execute(ProductConverter.toDomain(request));
        return ProductResponseJson.from(response);
    }
}
