package com.br.rank.list.app.usecases

import com.br.rank.list.app.exceptions.RestaurantNotFoundException
import com.br.rank.list.app.repositories.IProductRepository
import com.br.rank.list.app.repositories.IRestaurantCategoriesRepository
import com.br.rank.list.app.usecases.impl.FindByCategories
import com.br.rank.list.domains.Categories
import com.br.rank.list.domains.Product
import com.br.rank.list.domains.RestaurantCategories
import com.br.rank.list.infra.http.jsons.requests.CategoriesRequestJson
import com.fasterxml.jackson.core.type.TypeReference
import spock.lang.Specification
import utils.GetMockJson

class FindByCategoriesSpec extends Specification {

    def restaurantCategoriesRepository = Mock(IRestaurantCategoriesRepository)
    def getRestaurantOrThrowNotFound = Mock(IGetRestaurantOrThrowNotFound)
    def productRepository = Mock(IProductRepository)
    def findByCategories = new FindByCategories(restaurantCategoriesRepository, getRestaurantOrThrowNotFound, productRepository)

    def getMockJson = new GetMockJson()

    def "shouldFindByCategories"() {
        given: "a valid code"
        def code = "1"

        and: "a RestaurantCategories optional when searching for a valid RestaurantCategories code"
        def restaurantCategories = getMockJson.execute("domains/restaurant-categories", RestaurantCategories.class)
        restaurantCategoriesRepository.findByCode(_ as String) >> Optional.of(restaurantCategories)

        and: "a list of products when searched for by a code and Categories"
        def products = getMockJson.execute("domains/products-valid", new TypeReference<Collection<Product>>() {});
        productRepository.findAllByCodeAndCategories(_ as String, _ as Categories) >> products

        when: "run find by categories"
        def result = findByCategories.execute(code, Categories.from(restaurantCategories.values))

        then: "the result must be different from null and the list size must be equal to 3"
        assert result != null
        assert result.size() == 3

        and: "must be checked if the restaurant exists once"
        1 * getRestaurantOrThrowNotFound.execute(code)
    }

    def "shouldThrowRestaurantNotFoundException"() {
        given: "a invalid code"
        def code = "1"

        and: "a optional empty when searching for a invalid RestaurantCategories code"
        restaurantCategoriesRepository.findByCode(_ as String) >> Optional.empty()

        when: "run find by categories"
        findByCategories.execute(code, Categories.empty())

        then: "then you should throw RestaurantNotFoundException"
        thrown RestaurantNotFoundException

        and: "must be checked if the restaurant exists once"
        1 * getRestaurantOrThrowNotFound.execute(code)
    }

    def "shouldThrowIllegalArgumentException"() {
        given: "a valid code"
        def code = "1"

        and: "a RestaurantCategories optional when searching for a valid RestaurantCategories code"
        def restaurantCategories = getMockJson.execute("domains/restaurant-categories", RestaurantCategories.class)
        restaurantCategoriesRepository.findByCode(_ as String) >> Optional.of(restaurantCategories)

        and: "a list of products when searched for by a code and Categories"
        def products = getMockJson.execute("domains/products-valid", new TypeReference<Collection<Product>>() {});
        productRepository.findAllByCodeAndCategories(_ as String, _ as Categories) >> products

        when: "run find by invalid categories"
        def categoriesInvalidRequest = getMockJson.execute("requests/categories-invalid-request", CategoriesRequestJson.class);
        findByCategories.execute(code, Categories.from(categoriesInvalidRequest.values()))

        then: "then you should throw IllegalArgumentException"
        thrown IllegalArgumentException

        and: "must be checked if the restaurant exists once"
        1 * getRestaurantOrThrowNotFound.execute(code)
    }
}
