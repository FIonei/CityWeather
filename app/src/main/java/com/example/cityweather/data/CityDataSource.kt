package com.example.cityweather.data

import com.example.cityweather.domain.City

interface CityDataSource {

    fun getCities(): List<City>

    fun getCity(id: Long): City?

    fun setCity(city: City)
}