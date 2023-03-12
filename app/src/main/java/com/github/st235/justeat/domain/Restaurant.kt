package com.github.st235.justeat.domain

import com.github.st235.justeat.data.restaurants.FindRestaurantsResponse

data class Restaurant(
    val id: Long,
    val name: String,
    val description: String,
    val logo: String,
    val rating: Double
)

fun FindRestaurantsResponse.asRestaurantsList(): List<Restaurant> {
    return this.restaurants.map { response ->
        Restaurant(
            id = response.id,
            name = response.name,
            description = response.description,
            logo = response.logo,
            rating = response.rating
        )
    }
}
