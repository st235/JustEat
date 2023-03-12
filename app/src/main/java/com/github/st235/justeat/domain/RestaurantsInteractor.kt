package com.github.st235.justeat.domain

import com.github.st235.justeat.data.restaurants.RestaurantsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RestaurantsInteractor(
    private val restaurantsRepository: RestaurantsRepository
) {

    suspend fun findRestaurantsByPostCode(postcode: String): Result<List<Restaurant>>
        = withContext(Dispatchers.IO) {
        try {
            val data = restaurantsRepository.findRestaurantsByPostcode(postcode)
            Result.success(data.asRestaurantsList())
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

}
