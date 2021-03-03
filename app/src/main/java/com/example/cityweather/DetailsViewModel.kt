package com.example.cityweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cityweather.dataClasses.City
import com.example.cityweather.repositories.CitiesRepository

class DetailsViewModel(
    private val repository: CitiesRepository,
    cityId: Long
) : ViewModel() {

    val city = MutableLiveData<City>()

    val closeScreenEvent = LiveEvent()

    init {
        val city = repository.getCity(cityId)

        if (city != null) {
            this.city.value = city
        } else {
            closeScreenEvent(Unit)
        }

    }

    fun saveCity(editedCity: City) {
        repository.setCity(editedCity)
        closeScreenEvent()
    }
}