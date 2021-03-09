package com.example.cityweather.domain

class GetCitiesUseCase(private val repository: CitiesRepository) {
    operator fun invoke(): List<City> = repository.getCities()
}