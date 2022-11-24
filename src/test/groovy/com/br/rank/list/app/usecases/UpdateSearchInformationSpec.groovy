package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.ISearchInformationRepository
import com.br.rank.list.app.usecases.impl.UpdateSearchInformation
import com.br.rank.list.domains.SearchInformation
import spock.lang.Specification
import utils.GetMockJson

class UpdateSearchInformationSpec extends Specification {

    def searchInformationRepository = Mock(ISearchInformationRepository)
    def updateSearchInformation = new UpdateSearchInformation(searchInformationRepository)

    def getMockJson = new GetMockJson()

    def "shouldUpdateSearchInformation"() {
        given: "searchInformation when looking for a productId"
        def searchInformation = getMockJson.execute("domains/search-information", SearchInformation.class)
        searchInformationRepository.findByProductId(_ as String) >> Optional.of(searchInformation)

        when: "run update search information"
        updateSearchInformation.execute(searchInformation)

        then: "searchInformationRepository must be run once"
        1 * searchInformationRepository.save(_ as SearchInformation)
    }

    def "shouldCreateSearchInformation"() {
        given: "a optional empty when looking for a productId"
        searchInformationRepository.findByProductId(_ as String) >> Optional.empty()

        and: "a mock searchInformation"
        def searchInformation = getMockJson.execute("domains/search-information", SearchInformation.class)

        when: "run update search information"
        updateSearchInformation.execute(searchInformation)

        then: "searchInformationRepository must be run once"
        1 * searchInformationRepository.save(_ as SearchInformation)
    }
}
