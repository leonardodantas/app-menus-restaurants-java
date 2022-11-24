package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.IProductRepository
import com.br.rank.list.app.usecases.impl.RemoveAllPromotions
import com.br.rank.list.domains.Product
import spock.lang.Specification
import utils.GetMockJson

class RemoveAllPromotionsSpec extends Specification {

    def productRepository = Mock(IProductRepository)
    def getProductOrThrowNotFound = Mock(IGetProductOrThrowNotFound)
    def removeAllPromotions = new RemoveAllPromotions(productRepository, getProductOrThrowNotFound)

    def getMockJson = new GetMockJson()

    def "shouldRemoveAllPromotions"() {
        given: "a productId"
        def productId = "1"

        and: "a mock product when running the search product by productId"
        def product = getMockJson.execute("domains/product-with-promotion-valid", Product.class);
        getProductOrThrowNotFound.execute(_ as String) >> product

        when: "remove all promotion"
        removeAllPromotions.execute(productId)

        then: "the promotion must be removed and updated in the database"
        product.removePromotion()
        1 * productRepository.save(product)

    }

}
