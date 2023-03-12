package com.github.st235.justeat.data.restaurants

class RestaurantsRepository(
    private val restaurantsApi: RestaurantsApi
) {

    suspend fun findRestaurantsByPostcode(postcode: String): FindRestaurantsResponse {
        return restaurantsApi.findRestaurantsByPostCode(postcode)
    }

}