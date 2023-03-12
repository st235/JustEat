package com.github.st235.justeat.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.st235.justeat.domain.Restaurant
import com.github.st235.justeat.domain.RestaurantsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(
    private val restaurantsInteractor: RestaurantsInteractor
): ViewModel() {

    private val errorLiveData = MutableLiveData<Throwable?>()
    private val restaurantsLiveData = MutableLiveData<List<Restaurant>>()

    private var lastKnownFindRequest: Job? = null

    fun findRestaurantsByPostcode(postcode: String) {
        lastKnownFindRequest?.cancel()
        lastKnownFindRequest = viewModelScope.launch(Dispatchers.IO) {
            val restaurantsResult = restaurantsInteractor.findOpenRestaurantsByPostCode(postcode)

            viewModelScope.launch {
                if (restaurantsResult.isSuccess) {
                    restaurantsLiveData.value = restaurantsResult.getOrThrow()
                } else {
                    errorLiveData.value = restaurantsResult.exceptionOrNull()
                }
            }
        }
    }

    fun observeRestaurants(): LiveData<List<Restaurant>> = restaurantsLiveData

    fun observeErrors(): LiveData<Throwable?> = errorLiveData

}
