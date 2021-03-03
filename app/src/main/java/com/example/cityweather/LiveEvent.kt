package com.example.cityweather

class LiveEvent : SingleLiveEvent<Unit>() {

    operator fun invoke() {
        this.value = Unit
    }
}