package com.example.cityweather.repositories

import com.example.cityweather.dataClasses.City

class CitiesRepository {

        private val cities = mutableListOf(
            City(id = 0, name = "Paris", temperature = 3, commentary = null, weather = "rain"),
            City(id = 1, name = "Makao", temperature = 21, commentary = "ahh, nice", weather = "sunny"),
            City(id = 2, name = "London", temperature = 4, commentary = null, weather = "light_rain"),
            City(id = 3, name = "Rome", temperature = 8, commentary = null, weather = "light_rain"),
            City(id = 4, name = "Tokio", temperature = 12, commentary = "warmer than Moscow", weather = "sunny"),
            City(id = 5, name = "Praga", temperature = 3, commentary = null, weather = "clouds"),
            City(id = 6, name = "Novosibirsk", temperature = -18, commentary = "brr", weather = "sunny"),
            City(id = 7, name = "Moscow", temperature = 0, commentary = "not bad", weather = "clouds")
            )

        fun setCity(city: City) {
            val editedPersonIndex = cities.indexOfFirst { it.id == city.id }
            if (editedPersonIndex >= 0) {
                cities[editedPersonIndex] = city
            }
        }

        fun getCity(id: Long): City? = cities.firstOrNull { it.id == id }

        fun getCities(): List<City> = cities
}