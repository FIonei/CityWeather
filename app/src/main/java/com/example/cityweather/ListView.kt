package com.example.cityweather

import com.example.cityweather.dataClasses.City

interface ListView: BaseView {

    fun bindCitiesList(list: List<City>)

    fun openCityDetailsScreen(cityId: Long)
}