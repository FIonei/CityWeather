package com.example.cityweather.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cityweather.Adapters.CitiesAdapter
import com.example.cityweather.CityApplication
import com.example.cityweather.R
import com.example.cityweather.Repositories.CitiesRepository

class MainActivity : AppCompatActivity() {

    private lateinit var cityRepository: CitiesRepository

    private lateinit var cityList: RecyclerView

    private val adapter = CitiesAdapter {
        DetailsActivity.start(this, it.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityRepository = (application as CityApplication).cityRepository

        cityList = findViewById(R.id.cities_list)
        cityList.adapter = adapter
        cityList.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        adapter.cities = cityRepository.getCities()
    }
}
