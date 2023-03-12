package com.github.st235.justeat.di

import com.github.st235.justeat.BuildConfig
import com.github.st235.justeat.data.restaurants.RestaurantsApi
import com.github.st235.justeat.data.restaurants.RestaurantsRepository
import com.github.st235.justeat.domain.RestaurantsInteractor
import com.github.st235.justeat.presentation.MainViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val networkModule = module {

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

    single {
        RestaurantsRepository(get())
    }

}

private val domainModule = module {

    factory {
        RestaurantsInteractor(get())
    }

}

private val presentationModule = module {

    viewModel { MainViewModel(get()) }

}

val appModules = networkModule + domainModule + presentationModule
