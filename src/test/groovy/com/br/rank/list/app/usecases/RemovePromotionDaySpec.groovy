package com.br.rank.list.app.usecases

import com.br.rank.list.app.exceptions.PromotionNotExistException
import com.br.rank.list.app.repositories.IProductRepository
import com.br.rank.list.app.usecases.impl.RemovePromotionDay
import com.br.rank.list.domains.Days
import com.br.rank.list.domains.Product
import spock.lang.Specification
import utils.GetMockJson

class RemovePromotionDaySpec extends Specification {

    def productRepository = Mock(IProductRepository)
    def getProductOrThrowNotFound = Mock(IGetProductOrThrowNotFound)
    def removePromotionDay = new RemovePromotionDay(productRepository, getProductOrThrowNotFound)

    def getMockJson = new GetMockJson()

    def "shouldRemovePromotionDay"() {
        given: "a productId and day"
        def productId = "1"
        def day = Days.MONDAY

        and: "a mock product when looking for its productId"
        def product = getMockJson.execute("domains/product-with-promotion-valid", Product.class)
        getProductOrThrowNotFound.execute(_ as String) >> product

        when: "run remove promotion day"
        removePromotionDay.execute(productId, day)

        then: "the product must be saved only once"
        1 * productRepository.save(_ as Product)
    }

    def "shouldThrownPromotionNotExistException"() {
        given: "a productId and day"
        def productId = "1"
        def day = Days.FRIDAY

        and: "a mock product when looking for its productId"
        def product = getMockJson.execute("domains/product-with-promotion-valid", Product.class)
        getProductOrThrowNotFound.execute(_ as String) >> product

        when: "run remove promotion day"
        removePromotionDay.execute(productId, day)

        then: "thrown PromotionNotExistException"
        thrown PromotionNotExistException
    }
}
