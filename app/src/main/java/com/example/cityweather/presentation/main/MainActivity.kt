package com.example.cityweather.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.cityweather.adapters.CitiesAdapter
import com.example.cityweather.R
import com.example.cityweather.domain.City
import com.example.cityweather.presentation.detail.DetailsActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: ListViewModel by viewModels {
        ListViewModelFactory()
    }

    private lateinit var citiesList: RecyclerView

    private val adapter = CitiesAdapter {
        DetailsActivity.start(this, it.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.citiesList.observe(this, ::bindCitiesList)

        citiesList = findViewById(R.id.cities_list)
        citiesList.adapter = adapter
    }
    override fun onResume(){
        super.onResume()
        viewModel.loadCities()
    }

    private fun bindCitiesList(list: List<City>) {
        adapter.cities = list
    }
}
