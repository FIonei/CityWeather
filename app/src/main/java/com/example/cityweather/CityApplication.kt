package com.example.cityweather

import android.app.Application
import com.example.cityweather.repositories.CitiesRepository

class CityApplication: Application() {
    lateinit var cityRepository: CitiesRepository

    override fun onCreate() {
        super.onCreate()
        cityRepository = CitiesRepository()
    }
}