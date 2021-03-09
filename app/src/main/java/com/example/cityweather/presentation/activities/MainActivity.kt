package com.example.cityweather.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cityweather.CityApplication
import com.example.cityweather.presentation.presenters.ListPresenter
import com.example.cityweather.R
import com.example.cityweather.domain.City
import com.example.cityweather.presentation.CitiesAdapter

class MainActivity : AppCompatActivity(), ListView {

    private val presenter by lazy {
        ListPresenter((application as CityApplication).cityRepository)
    }
    private lateinit var cityList: RecyclerView

    private val adapter = CitiesAdapter {
        presenter.onCityClicked(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)

        cityList = findViewById(R.id.cities_list)
        cityList.adapter = adapter
        cityList.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
    }

    override fun bindCitiesList(list: List<City>) {
        adapter.cities = list
    }

    override fun openCityDetailsScreen(cityId: Long) {
        DetailsActivity.start(this, cityId)
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }
}
