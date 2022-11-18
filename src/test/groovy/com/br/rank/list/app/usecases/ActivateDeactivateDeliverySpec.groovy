package com.br.rank.list.app.usecases

import com.br.rank.list.app.usecases.impl.ActivateDeactivateDelivery
import com.br.rank.list.domains.Restaurant
import com.br.rank.list.infra.database.RestaurantRepository
import com.br.rank.list.infra.http.converters.DeliveryConverter
import com.br.rank.list.infra.http.jsons.requests.DeliveryRequest
import spock.lang.Specification
import utils.GetMockJson

class ActivateDeactivateDeliverySpec extends Specification {

    def restaurantRepository = Mock(RestaurantRepository)
    def getRestaurantOrThrowNotFound = Mock(IGetRestaurantOrThrowNotFound)

    def activateDeactivateDelivery = new ActivateDeactivateDelivery(restaurantRepository, getRestaurantOrThrowNotFound)

    def getMockJson = new GetMockJson();

    def "shouldActivateDelivery"() {
        given: "a valid code and a deliveryRequest"
        def code = "123"
        def deliveryRequest = getMockJson.execute("requests/delivery-request", DeliveryRequest.class)

        and: "deliveryRequest to delivery domain conversion"
        def delivery = DeliveryConverter.toDomain(deliveryRequest)

        and: "existing restaurant"
        def restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class)
        getRestaurantOrThrowNotFound.execute(_ as String) >> restaurant

        and: "save restaurant with delivery enabled"
        restaurantRepository.save(_ as Restaurant) >> Restaurant.activeDeliveryOf(restaurant, delivery)

        when: "run delivery activation"
        def result = activateDeactivateDelivery.activate(code, delivery);

        then: "the result must be different from null and equal to the desired delivery"
        assert result != null
        assert result == delivery

        and: "run only once"
        1 * restaurantRepository.save(_ as Restaurant) >> Restaurant.activeDeliveryOf(restaurant, delivery)
    }

    def "shouldDeactivateDelivery"() {
        given: "a valid code"
        def code = "123"

        and: "existing restaurant"
        def restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class)
        getRestaurantOrThrowNotFound.execute(_ as String) >> restaurant

        when: "run deactivation of delivery"
        activateDeactivateDelivery.deactivate(code)

        then: "save only once the restaurant with delivery disabled"
        1 * restaurantRepository.save(restaurant.disableDelivery())
    }
}
