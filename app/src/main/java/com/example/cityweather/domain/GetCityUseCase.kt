package com.example.cityweather.domain

class GetCityUseCase (private val repository: CitiesRepository) {

    operator fun invoke(id: Long): City? = repository.getCity(id)
}