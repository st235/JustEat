package com.github.st235.justeat.data.restaurants

import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantsApi {

    @GET("restaurants/bypostcode/{postcode}")
    fun findRestaurantsByPostCode(@Path("postcode") postcode: String): String

}
