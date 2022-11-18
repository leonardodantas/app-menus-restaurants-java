package com.br.rank.list.app.usecases

import com.br.rank.list.app.repositories.IProductRepository
import com.br.rank.list.app.repositories.IRestaurantCategoriesRepository
import com.br.rank.list.app.usecases.impl.FindByCategories
import com.br.rank.list.domains.Categories
import com.br.rank.list.domains.RestaurantCategories
import com.br.rank.list.infra.http.jsons.requests.CategoriesRequestJson
import spock.lang.Specification
import utils.GetMockJson

class FindByCategoriesSpec extends Specification {

    def restaurantCategoriesRepository = Mock(IRestaurantCategoriesRepository)
    def getRestaurantOrThrowNotFound = Mock(IGetRestaurantOrThrowNotFound)
    def productRepository = Mock(IProductRepository)
    def findByCategories = new FindByCategories(restaurantCategoriesRepository, getRestaurantOrThrowNotFound, productRepository)

    def getMockJson = new GetMockJson()

    def "shouldFindByCategories"(){
        def code = "1"
        def categoriesRequest = getMockJson.execute("requests/categories-request", CategoriesRequestJson.class)
        def restaurantCategories = getMockJson.execute("domains/restaurant-categories", RestaurantCategories.class)

        def result = findByCategories.execute(code, Categories.from(restaurantCategories.values))
    }
}
