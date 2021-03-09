package com.example.cityweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cityweather.dataClasses.City
import com.example.cityweather.repositories.CitiesRepository

class ListViewModel(private val repository: CitiesRepository) : ViewModel() {

    val citiesList = MutableLiveData<List<City>>()

    fun loadCities() {
        val cities = repository.getCities()

        citiesList.value = cities
    }
}