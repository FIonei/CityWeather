package com.example.cityweather.presentation.activities

import com.example.cityweather.domain.City
import com.example.cityweather.presentation.BaseView

interface ListView: BaseView {

    fun bindCitiesList(list: List<City>)

    fun openCityDetailsScreen(cityId: Long)
}