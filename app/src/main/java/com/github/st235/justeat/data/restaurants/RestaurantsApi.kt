package com.github.st235.justeat.data.restaurants

import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantsApi {

    @GET("restaurants/bypostcode/{postcode}")
    suspend fun findRestaurantsByPostCode(@Path("postcode") postcode: String): FindRestaurantsResponse

}
