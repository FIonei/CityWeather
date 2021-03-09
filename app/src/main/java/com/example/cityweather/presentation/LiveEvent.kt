package com.example.cityweather.presentation

class LiveEvent : SingleLiveEvent<Unit>() {

    operator fun invoke() {
        this.value = Unit
    }
}