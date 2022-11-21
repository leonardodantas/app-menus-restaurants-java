package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.ISearchRestaurantRepository
import com.br.rank.list.app.usecases.impl.GetSuggestionsRestaurants
import com.br.rank.list.domains.SearchRestaurant
import com.fasterxml.jackson.core.type.TypeReference
import spock.lang.Specification
import utils.GetMockJson

class GetSuggestionsRestaurantsSpec extends Specification {

    def searchRestaurantRepository = Mock(ISearchRestaurantRepository)
    def getSuggestionsRestaurants = new GetSuggestionsRestaurants(searchRestaurantRepository)

    def getMockJson = new GetMockJson()

    def "shouldGetSuggestionsRestaurants"() {
        given: "a valid search"
        def search = "HotDog"

        and: "a mock searchRestaurant list when running the search all restaurants by name containing"
        def searchRestaurants = getMockJson.execute("domains/search-restaurant-valid", new TypeReference<Collection<SearchRestaurant>>() {});
        searchRestaurantRepository.findByNameContaining(_ as String) >> searchRestaurants

        when: "get searchRestaurant with search"
        def result = getSuggestionsRestaurants.execute(search)

        then: "the result must be different from null and the size must be equal to 2"
        assert result != null
        assert result.size() == 2
    }

    def "shouldGetSuggestionsRestaurantsEmpty"() {
        given: "a valid search"
        def search = "HotDog"

        and: "a searchRestaurant list empty when running the search all restaurants by name containing"
        searchRestaurantRepository.findByNameContaining(_ as String) >> new ArrayList<SearchRestaurant>()

        when: "get searchRestaurant with search"
        def result = getSuggestionsRestaurants.execute(search)

        then: "the result must be different from null and the size must be equal to 0"
        assert result != null
        assert result.size() == 0
    }
}