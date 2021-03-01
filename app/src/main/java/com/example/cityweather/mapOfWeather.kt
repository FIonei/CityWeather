package com.example.cityweather

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat.getDrawable

fun getImage(nameOfIcon: String): Int {
    val map = mapOf("sunny" to R.drawable.sunny,
        "clouds" to R.drawable.clouds,
        "light_rain" to R.drawable.light_rain,
        "rain" to R.drawable.rain)

    return map.getValue(nameOfIcon)
}