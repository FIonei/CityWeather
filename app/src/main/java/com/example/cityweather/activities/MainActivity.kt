package com.example.cityweather.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cityweather.adapters.CitiesAdapter
import com.example.cityweather.CityApplication
import com.example.cityweather.ListViewModel
import com.example.cityweather.R
import com.example.cityweather.dataClasses.City
import com.example.cityweather.repositories.CitiesRepository

class MainActivity : AppCompatActivity() {

    private val viewModel: ListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                modelClass
                    .getConstructor(CitiesRepository::class.java)
                    .newInstance(
                        (application as CityApplication).cityRepository
                    )
        }
    }

    private lateinit var peopleList: RecyclerView

    private val adapter = CitiesAdapter {
        DetailsActivity.start(this, it.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.citiesList.observe(this, ::bindCityList)

        peopleList = findViewById(R.id.cities_list)
        peopleList.adapter = adapter
    }

    private fun bindCityList(list: List<City>) {
        adapter.cities = list
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCities()
    }
}
    /*private val viewModel: ListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                modelClass
                    .getConstructor(CitiesRepository::class.java)
                    .newInstance(
                        (application as CityApplication).cityRepository
                    )
        }
    }

    private lateinit var cityList: RecyclerView

    private val adapter = CitiesAdapter {
        //viewModel.openDetailsScreen(it)
        DetailsActivity.start(this, it.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.citiesList.observe(this, ::bindCities)
        //viewModel.openDetailsScreenEvent.observe(this, ::openDetailsScreen)

        cityList = findViewById(R.id.cities_list)
        cityList.adapter = adapter
    }
    override fun onResume(){
        super.onResume()
        viewModel.loadCities()
    }

    private fun bindCities(list: List<City>) {
        adapter.cities = list
    }

    //private fun openDetailsScreen(cityId: Long){
    //    DetailsActivity.start(this, cityId)
    //}
}*/
