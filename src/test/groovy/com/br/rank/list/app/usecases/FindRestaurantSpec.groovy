package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.IRestaurantRepository
import com.br.rank.list.app.usecases.impl.FindRestaurant
import com.br.rank.list.domains.Restaurant
import spock.lang.Specification
import utils.GetMockJson

class FindRestaurantSpec extends Specification {

    def restaurantRepository = Mock(IRestaurantRepository)
    def findRestaurant = new FindRestaurant(restaurantRepository)

    def getMockJson = new GetMockJson()

    def "shouldFindById"() {
        given: "a valid id"
        def id = "16"

        and: "a mock return when looking for the restaurant by id"
        def restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class)
        restaurantRepository.findById(_ as String) >> Optional.of(restaurant)

        when: "find restaurant by id"
        def result = findRestaurant.findById(id)

        then: "optional must contain a restaurant"
        assert result.isPresent()
    }

    def "shouldFindByCnpj"() {
        given: "a valid cnpj"
        def cnpj = "16.608.851/0001-51"

        and: "a mock return when looking for the restaurant by cnpj"
        def restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class)
        restaurantRepository.findByCnpj(_ as String) >> Optional.of(restaurant)

        when: "find restaurant by cnpj"
        def result = findRestaurant.findByCnpj(cnpj)

        then: "optional must contain a restaurant"
        assert result.isPresent()
    }

    def "shouldFindByCode"() {
        given: "a valid code"
        def code = "RESTAURANT_TEST"

        and: "a mock return when looking for the restaurant by code"
        def restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class)
        restaurantRepository.findByCode(_ as String) >> Optional.of(restaurant)

        when: "find restaurant by code"
        def result = findRestaurant.findByCode(code)

        then: "optional must contain a restaurant"
        assert result.isPresent()
    }
}
