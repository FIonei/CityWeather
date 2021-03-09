package com.example.cityweather.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cityweather.data.CityLocalDataSourceImpl
import com.example.cityweather.data.CityRepositoryImpl
import com.example.cityweather.domain.GetCityUseCase
import com.example.cityweather.domain.SetCityUseCase

class DetailsViewModelFactory (private val id: Long) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val cityDataSource = CityLocalDataSourceImpl()
        val cityRepository = CityRepositoryImpl(cityDataSource)
        val getCityUseCase = GetCityUseCase(cityRepository)
        val setCitysUseCase = SetCityUseCase(cityRepository)

        return modelClass
            .getConstructor(
                GetCityUseCase::class.java,
                SetCityUseCase::class.java,
                Long::class.java
            )
            .newInstance(getCityUseCase, setCitysUseCase, id)
    }
}