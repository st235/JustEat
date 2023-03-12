package com.github.st235.justeat.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.st235.justeat.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var restaurantsRecyclerView: RecyclerView
    private val restaurantsAdapter = RestaurantsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        restaurantsRecyclerView = findViewById(R.id.recycler_view)
        restaurantsRecyclerView.layoutManager = LinearLayoutManager(this)
        restaurantsRecyclerView.adapter = restaurantsAdapter

        mainViewModel.observeRestaurants()
            .observe(this) { restaurants ->
                restaurantsAdapter.setItems(restaurants)
            }

        mainViewModel.observeErrors()
            .observe(this) { throwable ->
                Log.e("HelloWorld", "Exception", throwable)
            }

        mainViewModel.findRestaurantsByPostcode("w22an")
    }
}
