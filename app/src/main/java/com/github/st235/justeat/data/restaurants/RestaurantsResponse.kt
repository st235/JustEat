package com.github.st235.justeat.data.restaurants

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FindRestaurantsResponse(
    @SerializedName("Area")
    @Expose
    val area: String,
    @SerializedName("Restaurants")
    @Expose
    val restaurants: List<RestaurantResponse>
)

data class RestaurantResponse(
    @SerializedName("Id")
    @Expose
    val id: Long,
    @SerializedName("Name")
    @Expose
    val name: String,
    @SerializedName("Description")
    @Expose
    val description: String,
    @SerializedName("LogoUrl")
    @Expose
    val logo: String,
    @SerializedName("RatingAverage")
    @Expose
    val rating: Double,
    @SerializedName("IsOpenNow")
    @Expose
    val isOpenNow: Boolean
)
