package com.br.rank.list.infra.http.controllers;

import com.br.rank.list.app.usecases.IUpdateProduct;
import com.br.rank.list.infra.http.converters.ProductConverter;
import com.br.rank.list.infra.http.jsons.requests.ProductRequestJson;
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
@RequestMapping("product")
@Api(tags = "Products")
public class UpdateProductController {

    private final IUpdateProduct updateProduct;

    public UpdateProductController(final IUpdateProduct updateProduct) {
        this.updateProduct = updateProduct;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Products", value = "Update product")
    public void execute(@Valid @RequestBody final ProductRequestJson request, @RequestParam final String id) {
        updateProduct.execute(id, ProductConverter.toDomain(request));
    }
}
