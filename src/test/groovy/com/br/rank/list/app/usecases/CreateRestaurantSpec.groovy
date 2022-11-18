package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.IRestaurantRepository
import com.br.rank.list.app.usecases.impl.CreateRestaurant
import com.br.rank.list.domains.Restaurant
import com.br.rank.list.infra.http.converters.RestaurantConverter
import com.br.rank.list.infra.http.jsons.requests.RestaurantRequestJson
import org.springframework.context.ApplicationEventPublisher
import spock.lang.Specification
import utils.GetMockJson

class CreateRestaurantSpec extends Specification {

    def restaurantRepository = Mock(IRestaurantRepository)
    def applicationEventPublisher = Mock(ApplicationEventPublisher)
    def createRestaurant = new CreateRestaurant(restaurantRepository, applicationEventPublisher)

    def getMockJson = new GetMockJson()

    def "shouldCreateRestaurant" () {
        given: "a restaurantRequest"
        def restaurantRequest = getMockJson.execute("requests/restaurant-request", RestaurantRequestJson)

        and: "convert restaurantRequest to restaurant domain conversion"
        def restaurant = RestaurantConverter.toDomain(restaurantRequest)

        and: "save and return restaurant valid"
        restaurantRepository.save(_ as Restaurant) >> restaurant

        when: "run create restaurant"
        def result = createRestaurant.execute(restaurant)

        then: "the result must be different from null and equal to the expected result"
        assert result != null
        assert result == restaurant

        and: "run once and wait for the following results"
        1 * applicationEventPublisher.publishEvent(_ as Restaurant) >> {
            Restaurant expected ->
                expected.cnpj == "75.334.352/0001-82" &&
                        expected.name == "Lanches Rui Barbosa"
        }
    }

}
