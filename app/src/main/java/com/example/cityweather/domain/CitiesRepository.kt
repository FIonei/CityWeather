package com.example.cityweather.domain

interface CitiesRepository {

        fun setCity(city: City)

        fun getCity(id: Long): City?

        fun getCities(): List<City>
}