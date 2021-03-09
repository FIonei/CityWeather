package com.example.cityweather.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cityweather.domain.City
import com.example.cityweather.domain.GetCitiesUseCase

class ListViewModel(private val getCitiesUseCase: GetCitiesUseCase) : ViewModel() {

    val citiesList = MutableLiveData<List<City>>()

    fun loadCities() {
        val cities = getCitiesUseCase()

        citiesList.value = cities
    }
}