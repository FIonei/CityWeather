package com.example.cityweather.dataClasses

data class City (
    val id: Long,
    val name: String,
    val temperature: Int,
    val commentary: String?,
    val weather: String = "sunny") {
}