package com.github.st235.justeat.domain

import com.github.st235.justeat.data.restaurants.RestaurantsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RestaurantsInteractor(
    private val restaurantsRepository: RestaurantsRepository
) {

    suspend fun findOpenRestaurantsByPostCode(postcode: String): Result<List<Restaurant>>
        = withContext(Dispatchers.IO) {
        try {
            val data = restaurantsRepository.findRestaurantsByPostcode(postcode)
            Result.success(
                data.restaurants.filter { it.isOpenNow }
                    .map { it.asRestaurant() }
            )
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

}
