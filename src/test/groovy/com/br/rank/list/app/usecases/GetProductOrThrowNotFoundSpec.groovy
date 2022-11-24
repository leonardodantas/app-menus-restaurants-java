package com.br.rank.list.app.usecases

import com.br.rank.list.app.exceptions.ProductNotFoundException
import com.br.rank.list.app.repositories.IProductRepository
import com.br.rank.list.app.usecases.impl.GetProductOrThrowNotFound
import com.br.rank.list.domains.Product
import spock.lang.Specification
import utils.GetMockJson

class GetProductOrThrowNotFoundSpec extends Specification {

    def productRepository = Mock(IProductRepository)
    def getRestaurantOrThrowNotFound = Mock(IGetRestaurantOrThrowNotFound)
    def getProductOrThrowNotFound = new GetProductOrThrowNotFound(productRepository, getRestaurantOrThrowNotFound)

    def getMockJson = new GetMockJson()

    def "shouldGetProduct"() {
        given: "a valid productId"
        def productId = "123"

        and: "a mock product when looking for its productId"
        def product = getMockJson.execute("domains/product-valid", Product.class)
        productRepository.findById(_ as String) >> Optional.of(product)

        when: "get product by productId"
        def result = getProductOrThrowNotFound.execute(productId)

        then: "result must be different from null"
        assert result != null

        and: "must be checked if the restaurant exists once"
        1 * getRestaurantOrThrowNotFound.execute(_ as String)
    }

    def "shouldThrowNotFound"() {
        given: "a valid productId"
        def productId = "123"

        and: "a optional empty when looking for its productId"
        productRepository.findById(_ as String) >> Optional.empty()

        when: "get product by productId"
        getProductOrThrowNotFound.execute(productId)

        then: "throw ProductNotFoundException"
        thrown ProductNotFoundException
    }

}
