package com.br.rank.list.app.usecases

import com.br.rank.list.app.messages.ISendProductMessage
import com.br.rank.list.app.usecases.impl.CreateProductEvents
import com.br.rank.list.domains.Product
import com.br.rank.list.infra.http.converters.ProductConverter
import com.br.rank.list.infra.http.jsons.requests.ProductRequestJson
import org.springframework.context.ApplicationEventPublisher
import spock.lang.Specification
import utils.GetMockJson

class CreateProductEventsSpec extends Specification {

    def applicationEventPublisher = Mock(ApplicationEventPublisher);
    def sendProductMessage = Mock(ISendProductMessage);
    def createProductEvents = new CreateProductEvents(applicationEventPublisher, sendProductMessage)

    def getMockJson = new GetMockJson()

    def "shouldCreateProductEvents"() {
        given: "a productRequest"
        def productRequest = getMockJson.execute("requests/product-request", ProductRequestJson.class)

        and: "convert productRequest to product domain conversion"
        def product = ProductConverter.toDomain(productRequest)

        when: "receive the product to trigger the events"
        createProductEvents.execute(product)

        then: "the event must be published once"
        1 * applicationEventPublisher.publishEvent(_ as Product) >> {
            Product expected ->
                expected.code == "LANCHON-B5634" && expected.name == "Cachorro quente especial" &&
                        expected.price == BigDecimal.valueOf(20)
        }

        and: "the message must be posted in Kafka once"
        1 * sendProductMessage.execute(_ as Product) >> {
            Product expected ->
                expected.code == "LANCHON-B5634" && expected.name == "Cachorro quente especial" &&
                        expected.price == BigDecimal.valueOf(20)
        }
    }
}
