package com.github.st235.justeat.di

import com.github.st235.justeat.BuildConfig
import com.github.st235.justeat.data.restaurants.RestaurantsApi
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE_JUST_EAT)
            .build()
    }

    single<RestaurantsApi> {
        val retrofit = get<Retrofit>()
        return@single retrofit.create(RestaurantsApi::class.java)
    }

}

val appModules = networkModule
