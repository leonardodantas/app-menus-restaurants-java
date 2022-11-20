package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.ISearchProductRepository
import com.br.rank.list.app.usecases.impl.GetSuggestionsProducts
import com.br.rank.list.domains.SearchProduct
import com.fasterxml.jackson.core.type.TypeReference
import spock.lang.Specification
import utils.GetMockJson

class GetSuggestionsProductsSpec extends Specification {

    def searchProductRepository = Mock(ISearchProductRepository)
    def getRestaurantOrThrowNotFound = Mock(IGetRestaurantOrThrowNotFound)
    def getSuggestionsProducts = new GetSuggestionsProducts(searchProductRepository, getRestaurantOrThrowNotFound)

    def getMockJson = new GetMockJson()

    def "shouldGetSuggestionsProducts"() {
        given: "a code and search"
        def code = "3"
        def search = "Hot dog"

        and: "a mock searchProduct list when running the search"
        def searchProducts = getMockJson.execute("domains/search-product-valid", new TypeReference<Collection<SearchProduct>>() {
        })
        searchProductRepository.findByCodeAndNameContaining(_ as String, _ as String) >> searchProducts

        when: "get suggestions products with code and search"
        def result = getSuggestionsProducts.execute(code, search)

        then: "the result must be different from null and the size must be equal to 3"
        assert !result.isEmpty()
        assert result.size() == 3

        and: "must be checked if the restaurant exists once"
        1 * getRestaurantOrThrowNotFound.execute(code)
    }

}
