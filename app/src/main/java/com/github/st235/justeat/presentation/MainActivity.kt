package com.github.st235.justeat.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.st235.justeat.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.observeRestaurants()
            .observe(this) { restaurants ->
                Log.d("HelloWorld", restaurants.toString())
            }

        mainViewModel.observeErrors()
            .observe(this) { throwable ->
                Log.e("HelloWorld", "Exception", throwable)
            }

        mainViewModel.findRestaurantsByPostcode("w22an")
    }
}
