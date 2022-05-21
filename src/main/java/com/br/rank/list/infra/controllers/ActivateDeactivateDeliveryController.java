package com.br.rank.list.infra.controllers;

import com.br.rank.list.app.usecases.IActivateDeactivateDelivery;
import com.br.rank.list.infra.converters.DeliveryConverter;
import com.br.rank.list.infra.jsons.requests.DeliveryRequest;
import com.br.rank.list.infra.jsons.responses.DeliveryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("restaurant/delivery")
@Api(tags = "Delivery")
public class ActivateDeactivateDeliveryController {

    private final IActivateDeactivateDelivery activateDeactivateDelivery;

    public ActivateDeactivateDeliveryController(final IActivateDeactivateDelivery activateDeactivateDelivery) {
        this.activateDeactivateDelivery = activateDeactivateDelivery;
    }


    @PostMapping("/activate")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(tags = "Delivery", value = "Activate Delivery")
    public DeliveryResponse activate(@RequestParam final String code, @Valid @RequestBody final DeliveryRequest request) {
        final var response = activateDeactivateDelivery.activate(code, DeliveryConverter.toDomain(request));
        return DeliveryResponse.from(response);
    }

    @PutMapping("/deactivate")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(tags = "Delivery", value = "Deactivate Delivery")
    public void deactivate(@RequestParam final String code) {
        activateDeactivateDelivery.deactivate(code);
    }
}
