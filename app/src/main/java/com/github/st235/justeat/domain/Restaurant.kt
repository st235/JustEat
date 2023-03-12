package com.github.st235.justeat.domain

import com.github.st235.justeat.data.restaurants.RestaurantResponse

data class Restaurant(
    val id: Long,
    val name: String,
    val description: String,
    val logo: String,
    val rating: Double
)

fun RestaurantResponse.asRestaurant(): Restaurant {
    return Restaurant(
        id = this.id,
        name = this.name,
        description = this.description,
        logo = this.logo,
        rating = this.rating
    )
}
