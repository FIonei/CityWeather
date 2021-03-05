package com.example.cityweather

import android.app.Application
import com.example.cityweather.data.CitiesRepository

class CityApplication: Application() {
    val cityRepository = CitiesRepository()
}