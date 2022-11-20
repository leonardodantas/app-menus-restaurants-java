package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.IRestaurantCategoriesRepository
import com.br.rank.list.app.usecases.impl.GetCategories
import com.br.rank.list.domains.RestaurantCategories
import spock.lang.Specification
import utils.GetMockJson

class GetCategoriesSpec extends Specification {

    def restaurantCategoriesRepository = Mock(IRestaurantCategoriesRepository)
    def getRestaurantOrThrowNotFound = Mock(IGetRestaurantOrThrowNotFound)
    def getCategories = new GetCategories(restaurantCategoriesRepository, getRestaurantOrThrowNotFound)

    def getMockJson = new GetMockJson()

    def  "shouldGetCategories"(){
        given:"a valid code"
        def code = "RESTAURANT_TEST"

        and: "a mock return when looking for the restaurantCategories by code"
        def restaurantCategories = getMockJson.execute("domains/restaurant-categories", RestaurantCategories.class);
        restaurantCategoriesRepository.findByCode(_ as String) >> Optional.of(restaurantCategories)

        when: "get categories by code"
        def result = getCategories.execute(code)

        then: "the number of categories is equal to 3"
        assert result.values.size() == 3

        and: "must be checked if the restaurant exists once"
        1 * getRestaurantOrThrowNotFound.execute(code)
    }

    def  "shouldGetCategoriesEmpty"(){
        given:"a valid code"
        def code = "RESTAURANT_TEST"

        and: "a mock return when looking for the restaurantCategories by code"
        restaurantCategoriesRepository.findByCode(_ as String) >> Optional.empty()

        when: "get categories by code"
        def result = getCategories.execute(code)

        then: "the number of categories is equal to 0"
        assert result.values.size() == 0

        and: "must be checked if the restaurant exists once"
        1 * getRestaurantOrThrowNotFound.execute(code)
    }
}
