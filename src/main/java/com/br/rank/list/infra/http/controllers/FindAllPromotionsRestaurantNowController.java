package com.br.rank.list.infra.http.controllers;

import com.br.rank.list.app.usecases.IFindAllPromotionsRestaurantNow;
import com.br.rank.list.infra.http.jsons.responses.ProductPromotionResponseJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequestMapping("promotions")
@RestController
@Api(tags = "Promotion")
public class FindAllPromotionsRestaurantNowController {

    private final IFindAllPromotionsRestaurantNow findAllPromotionsRestaurantNow;

    public FindAllPromotionsRestaurantNowController(final IFindAllPromotionsRestaurantNow findAllPromotionsRestaurantNow) {
        this.findAllPromotionsRestaurantNow = findAllPromotionsRestaurantNow;
    }

    @GetMapping("now")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Promotion", value = "Find promotions by restaurant now")
    public Collection<ProductPromotionResponseJson> execute(@RequestParam final String code) {
        final var products = findAllPromotionsRestaurantNow.execute(code);
        return products.stream().map(ProductPromotionResponseJson::from).toList();
    }

}
