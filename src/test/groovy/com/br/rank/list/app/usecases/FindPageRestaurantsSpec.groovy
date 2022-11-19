package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.IRestaurantRepository
import com.br.rank.list.app.usecases.impl.FindPageRestaurants
import com.br.rank.list.domains.Restaurant
import com.fasterxml.jackson.core.type.TypeReference
import org.springframework.data.domain.PageImpl
import spock.lang.Specification
import utils.GetMockJson

class FindPageRestaurantsSpec extends Specification {

    def restaurantRepository = Mock(IRestaurantRepository)
    def findPageRestaurants = new FindPageRestaurants(restaurantRepository)

    def getMockJson = new GetMockJson()

    def "shouldFindPageRestaurants"() {
        given: "a page and size"
        def page = 0
        def size = 20

        def restaurants = getMockJson.execute("domains/restaurants-valid", new TypeReference<Collection<Restaurant>>() {
        })

        and: "a page of active restaurants"
        restaurantRepository.findActiveRestaurants(_ as Integer, _ as Integer) >> new PageImpl<>(restaurants.stream().toList())

        when: "find page restaurants"
        def result = findPageRestaurants.execute(page, size)

        then: "result must be different from null and size equals 4"
        result != null
        result.size == 4
    }
}
