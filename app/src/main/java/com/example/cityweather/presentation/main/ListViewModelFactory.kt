package com.example.cityweather.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cityweather.data.CityLocalDataSourceImpl
import com.example.cityweather.data.CityRepositoryImpl
import com.example.cityweather.domain.GetCitiesUseCase

class ListViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val cityDataSource = CityLocalDataSourceImpl()
        val citiesRepository = CityRepositoryImpl(cityDataSource)
        val getCitiesUseCase = GetCitiesUseCase(citiesRepository)

        return modelClass
            .getConstructor(GetCitiesUseCase::class.java)
            .newInstance(getCitiesUseCase)
    }
}