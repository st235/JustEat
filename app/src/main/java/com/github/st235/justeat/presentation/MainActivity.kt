package com.github.st235.justeat.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.st235.justeat.BuildConfig
import com.github.st235.justeat.R
import com.github.st235.justeat.presentation.components.DelayedTextWatcher
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private val restaurantsAdapter = RestaurantsAdapter()

    private lateinit var restaurantsRecyclerView: RecyclerView
    private lateinit var postcodeInputEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        restaurantsRecyclerView = findViewById(R.id.recycler_view)
        postcodeInputEditText = findViewById(R.id.postcode_input_edit_text)

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

        postcodeInputEditText.addTextChangedListener(PostcodeInputTextWatcher())
    }

    private inner class PostcodeInputTextWatcher: DelayedTextWatcher(BuildConfig.DELAY_INPUT_POSTCODE) {

        override fun onDelayedTextChange(text: String?, delay: Long) {
            if (text == null) {
                return
            }

            mainViewModel.findRestaurantsByPostcode(text)
        }
    }
}
