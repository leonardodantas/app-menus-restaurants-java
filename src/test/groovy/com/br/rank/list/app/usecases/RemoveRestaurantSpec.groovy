package com.br.rank.list.app.usecases

import com.br.rank.list.app.exceptions.RestaurantNotFoundException
import com.br.rank.list.app.repositories.IRestaurantRepository
import com.br.rank.list.app.usecases.impl.RemoveRestaurant
import com.br.rank.list.domains.Restaurant
import spock.lang.Specification
import utils.GetMockJson

class RemoveRestaurantSpec extends Specification {

    def restaurantRepository = Mock(IRestaurantRepository)
    def removeRestaurant = new RemoveRestaurant(restaurantRepository)

    def getMockJson = new GetMockJson()

    def "shouldRemoveRestaurant"(){
        given: "a valid restaurantId"
        def restaurantId = "1"

        and: "a mock restaurant when looking for its restaurantId"
        def restaurant = getMockJson.execute("domains/restaurant-valid", Restaurant.class)
        restaurantRepository.findById(_ as String) >> Optional.of(restaurant)

        when: "run remove restaurant"
        removeRestaurant.execute(restaurantId)

        then: "the restaurant must be saved with the status of disabled"
        1 * restaurantRepository.save({
            Restaurant expected ->
                !expected.isActive()
        })
    }

    def "shouldRemoveRestaurant01"(){
        given: "a valid restaurantId"
        def restaurantId = "1"

        and: "a optional empty when looking for its restaurantId"
        restaurantRepository.findById(_ as String) >> Optional.empty()

        when: "run remove restaurant"
        removeRestaurant.execute(restaurantId)

        then: "thrown RestaurantNotFoundException"
        thrown RestaurantNotFoundException
    }
}
