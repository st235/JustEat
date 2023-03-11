package com.github.st235.justeat.di

import com.github.st235.justeat.BuildConfig
import com.github.st235.justeat.data.restaurants.RestaurantsApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    single {
        val gson = get<Gson>()

        Retrofit.Builder()
            .baseUrl(BuildConfig.URL_BASE_JUST_EAT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    single<RestaurantsApi> {
        val retrofit = get<Retrofit>()
        return@single retrofit.create(RestaurantsApi::class.java)
    }

}

val appModules = networkModule
