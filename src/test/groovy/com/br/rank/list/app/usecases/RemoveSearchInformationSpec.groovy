package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.ISearchInformationRepository
import com.br.rank.list.app.usecases.impl.RemoveSearchInformation
import spock.lang.Specification

class RemoveSearchInformationSpec extends Specification {

    def searchInformationRepository = Mock(ISearchInformationRepository)
    def removeSearchInformation = new RemoveSearchInformation(searchInformationRepository)

    def "shouldRemoveSearchInformationRepository"() {
        given: "a valid productId"
        def productId = "1"

        when: "remove search information by productId"
        removeSearchInformation.execute(productId)

        then: "searchInformationRepository must remove product by id"
        1 * searchInformationRepository.removeByProductId(_ as String)
    }
}
