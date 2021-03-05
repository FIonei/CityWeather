package com.example.cityweather.presentation.presenters

import com.example.cityweather.presentation.activities.ListView
import com.example.cityweather.domain.City
import com.example.cityweather.data.CitiesRepository

class ListPresenter(private val repository: CitiesRepository) : BasePresenter<ListView>() {

    fun onViewResumed() {
        val citiesList = repository.getCities()

        view?.bindCitiesList(citiesList)
    }

    fun onCityClicked(city: City) {
        view?.openCityDetailsScreen(city.id)
    }
}