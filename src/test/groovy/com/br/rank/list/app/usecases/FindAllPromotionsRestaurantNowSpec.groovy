package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.IProductRepository
import com.br.rank.list.app.usecases.impl.FindAllPromotionsRestaurantNow
import com.br.rank.list.domains.Product
import com.fasterxml.jackson.core.type.TypeReference
import spock.lang.Specification
import utils.GetMockJson

class FindAllPromotionsRestaurantNowSpec extends Specification {

    def productRepository = Mock(IProductRepository)
    def getRestaurantOrThrowNotFound = Mock(IGetRestaurantOrThrowNotFound)
    def findAllPromotionsRestaurantNow = new FindAllPromotionsRestaurantNow(productRepository, getRestaurantOrThrowNotFound)

    def getMockJson = new GetMockJson()

    def "shouldFindAllPromotionsRestaurantNow" (){
        given: "a code"
        def code = "1"

        and: "a list of products returned when searching by code"
        def products = getMockJson.execute("domains/products-with-promotion-valid", new TypeReference<Collection<Product>>(){})
        productRepository.findAllByCodeAndPromotionTrueCacheable(_ as String) >> products

        when: "run find all promotions restaurant now"
        def result = findAllPromotionsRestaurantNow.execute(code)

        then: "the result must be different from null and the list size must be equal to 3"
        assert result != null
        assert result.size() == 7
    }

}
