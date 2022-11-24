package com.br.rank.list.app.usecases

import com.br.rank.list.app.exceptions.RestaurantNotFoundException
import com.br.rank.list.app.repositories.IRestaurantRepository
import com.br.rank.list.app.usecases.impl.OpenAndCloseRestaurant
import com.br.rank.list.app.usecases.impl.OpenCloseRestaurant
import com.br.rank.list.domains.Restaurant
import spock.lang.Specification
import utils.GetMockJson

class OpenAndCloseRestaurantSpec extends Specification {

    def restaurantRepository = Mock(IRestaurantRepository)
    def openAndCloseRestaurant = new OpenAndCloseRestaurant(restaurantRepository)

    def getMockJson = new GetMockJson()

    def "shouldOpenRestaurant"() {
        given: "a valid id"
        def id = "1"

        and: "a mock restaurant when running the search restaurant by id"
        def restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class)
        restaurantRepository.findById(id) >> Optional.of(restaurant)

        when: "run to open the restaurant"
        def result = openAndCloseRestaurant.execute(id, OpenCloseRestaurant.OPEN)

        then: "the result must be different from null and restaurant must be open"
        assert result != null
        assert result.isOpen()

        and: "the restaurant must be saved only once with the status of open"
        1 * restaurantRepository.save(restaurant.open()) >> restaurant.open()
    }

    def "shouldCloseRestaurant"() {
        given: "a valid id"
        def id = "1"

        and: "a mock restaurant when running the search restaurant by id"
        def restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class)
        restaurantRepository.findById(id) >> Optional.of(restaurant)

        when: "run to close the restaurant"
        def result = openAndCloseRestaurant.execute(id, OpenCloseRestaurant.CLOSE)

        then: "the result must be different from null and restaurant must be close"
        assert result != null
        assert result.isClosed()

        and: "the restaurant must be saved only once with the status of open"
        1 * restaurantRepository.save(restaurant.close()) >> restaurant.close()
    }

    def "shouldThrowRestaurantNotFound"() {
        given: "a valid id"
        def id = "1"

        and: "a mock optional empty when running the search restaurant by id"
        restaurantRepository.findById(id) >> Optional.empty()

        when: "run to close the restaurant"
        openAndCloseRestaurant.execute(id, OpenCloseRestaurant.CLOSE)

        then: "thrown RestaurantNotFoundException"
        thrown RestaurantNotFoundException

        and: "the restaurant should never be saved"
        0 * restaurantRepository.save(_ as Restaurant)
    }
}
