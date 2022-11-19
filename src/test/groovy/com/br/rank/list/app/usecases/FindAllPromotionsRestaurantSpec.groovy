package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.IProductRepository
import com.br.rank.list.app.usecases.impl.FindAllPromotionsRestaurant
import com.br.rank.list.domains.Product
import com.fasterxml.jackson.core.type.TypeReference
import spock.lang.Specification
import utils.GetMockJson

class FindAllPromotionsRestaurantSpec extends Specification {

    def productRepository = Mock(IProductRepository)
    def findAllPromotionsRestaurant = new FindAllPromotionsRestaurant(productRepository)

    def getMockJson = new GetMockJson()

    def "shouldFindAllPromotionsRestaurant"() {
        given: "a code"
        def code = "1"

        and: "a list of products returned when searching by code"
        def products = getMockJson.execute("domains/products-valid", new TypeReference<Collection<Product>>() {})
        productRepository.findByCodeAndPromotionTrue(_ as String) >> products

        when: "run find all promotions restaurant"
        def result = findAllPromotionsRestaurant.execute(code)

        then: "the result must be different from null and the list size must be equal to 3"
        assert result != null
        assert result.size() == 3
    }
}
