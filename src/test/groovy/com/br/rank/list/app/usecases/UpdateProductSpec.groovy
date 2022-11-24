package com.br.rank.list.app.usecases

import com.br.rank.list.app.exceptions.ProductNotFoundException
import com.br.rank.list.app.repositories.IProductRepository
import com.br.rank.list.app.usecases.impl.UpdateProduct
import com.br.rank.list.domains.Product
import com.br.rank.list.infra.http.converters.ProductConverter
import com.br.rank.list.infra.http.jsons.requests.ProductRequestJson
import spock.lang.Specification
import utils.GetMockJson

class UpdateProductSpec extends Specification {

    def productRepository = Mock(IProductRepository)
    def getRestaurantOrThrowNotFound = Mock(IGetRestaurantOrThrowNotFound)
    def updateProductEvents = Mock(IUpdateProductEvents)
    def updateProduct = new UpdateProduct(productRepository, getRestaurantOrThrowNotFound, updateProductEvents)

    def getMockJson = new GetMockJson()

    def "shouldUpdateProduct"() {
        given: "a valid id"
        def id = "62cfff959ff3921d08df7920"

        and: "a product when searching by id"
        def existingProduct = getMockJson.execute("domains/product-valid", Product.class)
        productRepository.findById(_ as String) >> Optional.of(existingProduct)

        and: "a product request that will be converted into a product domain"
        def productRequest = getMockJson.execute("requests/product-request", ProductRequestJson.class)
        def productToUpdate = ProductConverter.toDomain(productRequest)

        and: "updating a product"
        productRepository.save(_ as Product) >> productToUpdate.withId(id)

        when: "update the product by id and with a product"
        updateProduct.execute(id, productToUpdate)

        then: "update events should be triggered with the expected results"
        1 * updateProductEvents.execute({
            Product expected ->
                expected.price == BigDecimal.valueOf(20) &&
                        expected.code == "LANCHON-B5634" && expected.name == "Cachorro quente especial"
        })
    }


    def "shouldThrownProductNotFoundException"() {
        given: "a id"
        def id = "1"

        and: "a optional empty when searching by id"
        productRepository.findById(_ as String) >> Optional.empty()

        and: "a product request that will be converted into a product domain"
        def productRequest = getMockJson.execute("requests/product-request", ProductRequestJson.class)
        def productToUpdate = ProductConverter.toDomain(productRequest)

        when: "update the product by id and with a product"
        updateProduct.execute(id, productToUpdate)

        then: "thrown ProductNotFoundException"
        thrown ProductNotFoundException
    }
}
