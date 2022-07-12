package com.br.rank.list.infra.feign;

import com.br.rank.list.domains.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@FeignClient(value = "searchProvider", url = "http://localhost:8082", fallback = SearchProductsAllRestaurantsFallback.class)
public interface ISearchProductsAllRestaurantsFeign {

    @GetMapping("search")
    Collection<Product> execute(@RequestParam String q);
}
