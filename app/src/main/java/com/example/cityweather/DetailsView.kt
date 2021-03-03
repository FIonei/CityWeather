package com.example.cityweather

import com.example.cityweather.dataClasses.City

interface DetailsView : BaseView {
    fun bindCity(city: City)

    fun closeScreen()
}