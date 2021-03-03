package com.example.cityweather

import com.example.cityweather.dataClasses.City
import com.example.cityweather.repositories.CitiesRepository

class ListPresenter(private val repository: CitiesRepository) : BasePresenter<ListView>() {

    fun onViewResumed() {
        val citiesList = repository.getCities()

        view?.bindCitiesList(citiesList)
    }

    fun onCityClicked(city: City) {
        view?.openCityDetailsScreen(city.id)
    }
}