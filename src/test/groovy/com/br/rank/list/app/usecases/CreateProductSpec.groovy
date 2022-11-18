package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.IProductRepository
import com.br.rank.list.app.usecases.impl.CreateProduct
import com.br.rank.list.domains.Product
import com.br.rank.list.infra.http.converters.ProductConverter
import com.br.rank.list.infra.http.jsons.requests.ProductRequestJson
import spock.lang.Specification
import utils.GetMockJson

class CreateProductSpec extends Specification {

    def productRepository = Mock(IProductRepository)
    def getRestaurantOrThrowNotFound = Mock(IGetRestaurantOrThrowNotFound)
    def createProductEvents = Mock(ICreateProductEvents)
    def createProduct = new CreateProduct(productRepository, getRestaurantOrThrowNotFound, createProductEvents)

    def getMockJson = new GetMockJson()

    def "shouldCreateProduct"() {
        given: "a productRequest"
        def productRequest = getMockJson.execute("requests/product-request", ProductRequestJson.class)

        and: "convert productRequest to product domain conversion"
        def product = ProductConverter.toDomain(productRequest)

        and: "save and return product valid"
        productRepository.save(_ as Product) >> product

        when: "run create product"
        def result = createProduct.execute(product)

        then: "the result must be different from null and equal to the expected product"
        result != null
        result == product

        and: "run once and wait for the following results"
        1 * createProductEvents.execute(_ as Product) >> {
            Product expected ->
                expected.code == "LANCHON-B5634" && expected.name == "Cachorro quente especial" &&
                        expected.price == BigDecimal.valueOf(20)
        }
    }
}
