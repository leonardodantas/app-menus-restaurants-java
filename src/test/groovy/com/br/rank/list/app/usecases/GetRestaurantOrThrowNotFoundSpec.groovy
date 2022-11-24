package com.br.rank.list.app.usecases

import com.br.rank.list.app.exceptions.RestaurantNotFoundException
import com.br.rank.list.app.repositories.IRestaurantRepository
import com.br.rank.list.app.usecases.impl.GetRestaurantOrThrowNotFound
import com.br.rank.list.domains.Restaurant
import spock.lang.Specification
import utils.GetMockJson

class GetRestaurantOrThrowNotFoundSpec extends Specification {

    def restaurantRepository = Mock(IRestaurantRepository)
    def getRestaurantOrThrowNotFound = new GetRestaurantOrThrowNotFound(restaurantRepository)

    def getMockJson = new GetMockJson()

    def "shouldGetRestaurant" () {
        given: "a valid code"
        def code = "1"

        and: "a mock product when looking for its code"
        def restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class);
        restaurantRepository.findByCode(_ as String) >> Optional.of(restaurant)

        when: "get restaurant by code"
        def result = getRestaurantOrThrowNotFound.execute(code)

        then: "result must be different from null"
        assert result != null
    }

    def "shouldThrowNotFound" () {
        given: "a valid code"
        def code = "1"

        and: "a optional empty when looking for its code"
        restaurantRepository.findByCode(_ as String) >> Optional.empty()

        when: "get restaurant by code"
        getRestaurantOrThrowNotFound.execute(code)

        then: "throw RestaurantNotFoundException"
        thrown RestaurantNotFoundException
    }

}
