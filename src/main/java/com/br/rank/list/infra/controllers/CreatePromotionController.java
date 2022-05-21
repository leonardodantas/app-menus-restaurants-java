package com.br.rank.list.infra.controllers;

import com.br.rank.list.app.usecases.ICreatePromotion;
import com.br.rank.list.infra.converters.PromotionConverter;
import com.br.rank.list.infra.jsons.requests.PromotionRequestJson;
import com.br.rank.list.infra.jsons.responses.ProductPromotionResponseJson;
import com.br.rank.list.infra.jsons.responses.ProductResponseJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("promotion")
@RestController
@Api(tags = "Promotion")
public class CreatePromotionController {

    private final ICreatePromotion createPromotion;

    public CreatePromotionController(final ICreatePromotion createPromotion) {
        this.createPromotion = createPromotion;
    }

    @PostMapping
    @ApiOperation(tags = "Promotion", value = "Create a new promotion")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductPromotionResponseJson execute(@RequestParam final String productId,
                                                @Valid @RequestBody final PromotionRequestJson request) {
        final var response = createPromotion.execute(productId, PromotionConverter.toDomain(request));
        return ProductPromotionResponseJson.from(response);
    }
}
