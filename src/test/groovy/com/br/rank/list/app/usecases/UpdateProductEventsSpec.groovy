package com.br.rank.list.app.usecases

import com.br.rank.list.app.messages.ISendProductMessage
import com.br.rank.list.app.usecases.impl.UpdateProductEvents
import com.br.rank.list.domains.Product
import com.br.rank.list.infra.http.converters.ProductConverter
import com.br.rank.list.infra.http.jsons.requests.ProductRequestJson
import org.springframework.context.ApplicationEventPublisher
import spock.lang.Specification
import utils.GetMockJson

class UpdateProductEventsSpec extends Specification {

    def applicationEventPublisher = Mock(ApplicationEventPublisher)
    def sendProductMessage = Mock(ISendProductMessage)
    def updateProductEvents = new UpdateProductEvents(applicationEventPublisher, sendProductMessage)

    def getMockJson = new GetMockJson()

    def "shouldUpdateProductEvents"() {
        given: "a product request converted to a product domain"
        def productRequest = getMockJson.execute("requests/product-request", ProductRequestJson.class);
        def product = ProductConverter.toDomain(productRequest)

        when: "run update product events"
        updateProductEvents.execute(product)

        then: "applicationEventPublisher must be executed twice"
        2 * applicationEventPublisher.publishEvent(_)

        and: "sendProductMessage must be executed once"
        1 * sendProductMessage.execute(_ as Product)
    }
}
