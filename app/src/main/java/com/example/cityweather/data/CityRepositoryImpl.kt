package com.example.cityweather.data

import com.example.cityweather.domain.CitiesRepository
import com.example.cityweather.domain.City

class CityRepositoryImpl (private val cityDataSource: CityDataSource) : CitiesRepository {

    override fun setCity(city: City) = cityDataSource.setCity(city)

    override fun getCity(id: Long): City? = cityDataSource.getCity(id)

    override fun getCities(): List<City> = cityDataSource.getCities()
}