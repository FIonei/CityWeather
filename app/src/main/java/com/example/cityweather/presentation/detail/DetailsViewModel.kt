package com.example.cityweather.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cityweather.domain.City
import com.example.cityweather.domain.GetCityUseCase
import com.example.cityweather.domain.SetCityUseCase
import com.example.cityweather.presentation.LiveEvent

class DetailsViewModel(
    getCityUseCase: GetCityUseCase,
    private val setCityUseCase: SetCityUseCase,
    cityId: Long
) : ViewModel() {

    val city = MutableLiveData<City>()

    val closeScreenEvent = LiveEvent()

    init {
        val city = getCityUseCase(cityId)

        if (city != null) {
            this.city.value = city
        } else {
            closeScreenEvent(Unit)
        }

    }

    fun saveCity(editedCity: City) {
        setCityUseCase(editedCity)
        closeScreenEvent()
    }
}