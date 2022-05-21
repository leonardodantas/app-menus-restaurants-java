package com.br.rank.list.infra.controllers;

import com.br.rank.list.app.usecases.IRemoveProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
@Api(tags = "Products")
public class RemoveProductController {

    private final IRemoveProduct removeProduct;

    public RemoveProductController(final IRemoveProduct removeProduct) {
        this.removeProduct = removeProduct;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Products", value = "Remove product")
    public void execute(@RequestParam final String id, @RequestParam final String code) {
        removeProduct.execute(id, code);
    }
}
