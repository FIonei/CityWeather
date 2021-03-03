package com.example.cityweather

import com.example.cityweather.dataClasses.City
import com.example.cityweather.repositories.CitiesRepository

class DetailPresenter(
    private val repository: CitiesRepository,
    private val personId: Long
) : BasePresenter<DetailsView>() {

    override fun onViewAttached() {
        val city = repository.getCity(personId)

        if (city != null) {
            view?.bindCity(city)
        } else {
            view?.closeScreen()
        }

    }

    fun saveCity(city: City) {
        repository.setCity(city)
        view?.closeScreen()
    }
}