package com.example.cityweather.presentation.activities

import com.example.cityweather.domain.City
import com.example.cityweather.presentation.BaseView

interface DetailsView : BaseView {
    fun bindCity(city: City)

    fun closeScreen()
}