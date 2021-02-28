package com.example.cityweather.DataClasses

data class City (
    val id: Long,
    val name: String,
    val temperature: Int,
    val commentary: String?) {
}