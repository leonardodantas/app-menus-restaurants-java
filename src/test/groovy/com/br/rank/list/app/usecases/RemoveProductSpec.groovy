package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.IProductRepository
import com.br.rank.list.app.usecases.impl.RemoveProduct
import spock.lang.Specification

class RemoveProductSpec extends Specification {

    def productRepository = Mock(IProductRepository)
    def getRestaurantOrThrowNotFound = Mock(IGetRestaurantOrThrowNotFound)
    def removeProductEvents = Mock(IRemoveProductEvents)
    def removeProduct = new RemoveProduct(productRepository, getRestaurantOrThrowNotFound, removeProductEvents)

    def "shouldRemoveProduct"() {
        given: "a id and code"
        def id = "1"
        def code = "321"

        when: "run remove product with id and code"
        removeProduct.execute(id, code)

        then: "must check if the restaurant exists once"
        1 * getRestaurantOrThrowNotFound.execute(code)

        and: "remove product by id"
        1 * productRepository.removeById(id)

        and: "events the product removal events"
        1 * removeProductEvents.execute(id, code)
    }
}
