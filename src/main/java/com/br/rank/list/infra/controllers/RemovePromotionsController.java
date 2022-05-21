package com.br.rank.list.infra.controllers;

import com.br.rank.list.app.usecases.IRemoveAllPromotions;
import com.br.rank.list.app.usecases.IRemovePromotionDay;
import com.br.rank.list.domains.Days;
import com.br.rank.list.infra.jsons.requests.DaysRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("promotion")
@Api(tags = "Promotion")
public class RemovePromotionsController {

    private final IRemoveAllPromotions removeAllPromotions;
    private final IRemovePromotionDay removePromotionDay;

    public RemovePromotionsController(final IRemoveAllPromotions removeAllPromotions, final IRemovePromotionDay removePromotionDay) {
        this.removeAllPromotions = removeAllPromotions;
        this.removePromotionDay = removePromotionDay;
    }

    @DeleteMapping("product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Promotion", value = "Remove all promotions by productId")
    public void deleteAll(@PathVariable final String productId) {
        removeAllPromotions.execute(productId);
    }

    @DeleteMapping("product/{productId}/day/{day}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Promotion", value = "Remove promotion by productId and day")
    public void deletePromotionDay(@PathVariable final String productId, @PathVariable final DaysRequest day) {
        removePromotionDay.execute(productId, Days.valueOf(day.name()));
    }
}
