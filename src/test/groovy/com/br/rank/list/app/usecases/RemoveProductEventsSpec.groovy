package com.br.rank.list.app.usecases

import com.br.rank.list.app.messages.IRemoveProductMessage
import com.br.rank.list.app.usecases.impl.RemoveProductEvents
import org.springframework.context.ApplicationEventPublisher
import spock.lang.Specification

class RemoveProductEventsSpec extends Specification {

    def applicationEventPublisher = Mock(ApplicationEventPublisher);
    def removeProductMessage = Mock(IRemoveProductMessage)
    def removeProductEvents = new RemoveProductEvents(applicationEventPublisher, removeProductMessage)

    def "shouldExecuteRemoveProductsEvents"() {
        given: "a id and code"
        def id = "1"
        def code = "RESTAURANT_TEST"

        when: "to fire the events to remove the product"
        removeProductEvents.execute(id, code)

        then: "applicationEventPublisher must run once"
        1 * applicationEventPublisher.publishEvent(_)

        and: "removeProductMessage must run once"
        1 * removeProductMessage.execute(_)
    }
}
