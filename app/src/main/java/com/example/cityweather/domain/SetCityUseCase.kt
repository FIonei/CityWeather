package com.example.cityweather.domain

class SetCityUseCase (private val repository: CitiesRepository){
    operator fun invoke(city: City) {
        repository.setCity(city)
    }
}