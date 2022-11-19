package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.IProductRepository
import com.br.rank.list.app.usecases.impl.FindPageProducts
import com.br.rank.list.domains.Product
import com.fasterxml.jackson.core.type.TypeReference
import org.springframework.data.domain.PageImpl
import spock.lang.Specification
import utils.GetMockJson

class FindPageProductsSpec extends Specification {

    def productRepository = Mock(IProductRepository)
    def getRestaurantOrThrowNotFound = Mock(IGetRestaurantOrThrowNotFound)
    def findPageProducts = new FindPageProducts(productRepository, getRestaurantOrThrowNotFound)

    def getMockJson = new GetMockJson()

    def "shouldFindPageProducts"() {
        given: "a valid code, a page and a size"
        def code = "1"
        def page = 0
        def size = 20

        and: "productPage when find all products by code, a page and a size"
        def products = getMockJson.execute("domains/products-valid", new TypeReference<Collection<Product>>() {});
        def productPage = new PageImpl<>(products.stream().toList());
        productRepository.findAllByCode(_ as String, _ as Integer, _ as Integer) >> productPage

        when: "run find page products"
        def result = findPageProducts.execute(code, page, size)

        then: "the result must be different from null and the list size must be equal to 3"
        assert result != null
        assert result.totalElements == 3

        and: "must be checked if the restaurant exists once"
        1 * getRestaurantOrThrowNotFound.execute(code)
    }
}
