package com.example.cityweather.presentation.presenters

import com.example.cityweather.presentation.activities.DetailsView
import com.example.cityweather.domain.City
import com.example.cityweather.data.CitiesRepository

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