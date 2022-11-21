package com.br.rank.list.app.usecases

import com.br.rank.list.app.rest.ISearchProductsAllRestaurantsRest
import com.br.rank.list.app.usecases.impl.GetSuggestionsProductsAllRestaurants
import com.br.rank.list.domains.Product
import com.fasterxml.jackson.core.type.TypeReference
import spock.lang.Specification
import utils.GetMockJson

class GetSuggestionsProductsAllRestaurantsSpec extends Specification {

    def searchProductsAllRestaurantsRest = Mock(ISearchProductsAllRestaurantsRest)
    def getSuggestionsProductsAllRestaurants = new GetSuggestionsProductsAllRestaurants(searchProductsAllRestaurantsRest)

    def getMockJson = new GetMockJson()

    def "shouldGetSuggestionsProductsAllRestaurants"() {
        given: "a valid search"
        def search = "Hot Dog"

        and: "a mock searchProduct list when running the search all restaurants"
        def searchProducts = getMockJson.execute("domains/products-valid", new TypeReference<Collection<Product>>() {})
        searchProductsAllRestaurantsRest.execute(_ as String) >> searchProducts

        when: "get suggestions products all restaurant with search"
        def result = getSuggestionsProductsAllRestaurants.execute(search)

        then: "the result must be different from null and the size must be equal to 3"
        assert result != null
        assert result.size() == 3
    }

    def "shouldGetSuggestionsProductsAllRestaurantsEmpty"() {
        given: "a valid search"
        def search = "Hot Dog"

        and: "a mock searchProduct list empty when running the search all restaurants"
        searchProductsAllRestaurantsRest.execute(_ as String) >> new ArrayList<Product>()

        when: "get suggestions products all restaurant with search"
        def result = getSuggestionsProductsAllRestaurants.execute(search)

        then: "the result must be different from null and the size must be equal to 0"
        assert result != null
        assert result.size() == 0
    }
}
