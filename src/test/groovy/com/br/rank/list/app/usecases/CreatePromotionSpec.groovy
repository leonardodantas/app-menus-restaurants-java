package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.IProductRepository
import com.br.rank.list.app.usecases.impl.CreatePromotion
import com.br.rank.list.domains.Product
import com.br.rank.list.infra.http.converters.PromotionConverter
import com.br.rank.list.infra.http.jsons.requests.PromotionRequestJson
import spock.lang.Specification
import utils.GetMockJson

class CreatePromotionSpec extends Specification {

    def productRepository = Mock(IProductRepository)
    def getProductOrThrowNotFound = Mock(IGetProductOrThrowNotFound)
    def createPromotion = new CreatePromotion(productRepository, getProductOrThrowNotFound)

    def getMockJson = new GetMockJson()

    def "shouldCreatePromotion"() {
        given: "a productId and promotionRequest"
        def productId = "1"
        def promotionRequest = getMockJson.execute("requests/promotion-request", PromotionRequestJson)

        and: "promotionRequest to promotion domain conversion"
        def promotion = PromotionConverter.toDomain(promotionRequest)

        and: "existing product"
        def product = getMockJson.execute("domains/product-valid", Product)
        getProductOrThrowNotFound.execute(_ as String) >> product

        and: "an update to the product with promotion"
        def productWithPromotion = Product.createPromotionOf(product, promotion)
        productRepository.saveProductWithPromotion(_ as Product) >> productWithPromotion

        when: "run create promotion"
        def result = createPromotion.execute(productId, promotion)

        then: "the result must be different from null and equal to productWithPromotion"
        assert result != null
        assert result == productWithPromotion

    }
}
