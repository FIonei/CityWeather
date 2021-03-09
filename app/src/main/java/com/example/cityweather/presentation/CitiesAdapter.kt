package com.example.cityweather.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.cityweather.domain.City
import com.example.cityweather.R
import com.example.cityweather.getImage

class CitiesAdapter(private val onItemClick: (City) -> Unit) : RecyclerView.Adapter<CitiesHolder>() {

    var cities: List<City> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_city, parent, false)
        return CitiesHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: CitiesHolder, position: Int) {
        val city = cities[position]
        holder.bind(city)
    }
    override fun getItemCount(): Int = cities.count()
}

class CitiesHolder(itemView: View, private val onItemClick: (City) -> Unit) : RecyclerView.ViewHolder(itemView) {

    private val nameText = itemView.findViewById<TextView>(R.id.name_text)
    private val temperatureText = itemView.findViewById<TextView>(R.id.temperature_text)
    private val commentaryText = itemView.findViewById<TextView>(R.id.commentary_input)
    private val weatherIcon = itemView.findViewById<ImageView>(R.id.weather_icon)

    fun bind(city: City) {
        nameText.text = itemView.context.getString(R.string.name_format, city.name)
        if (city.temperature > 0) {temperatureText.text = itemView.context.getString(R.string.plus_format, city.temperature)
            temperatureText.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
            commentaryText.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
        }
        else {
            temperatureText.text = itemView.context.getString(R.string.zero_format, city.temperature)
            temperatureText.setTextColor(ContextCompat.getColor(itemView.context, R.color.blue))
            commentaryText.setTextColor(ContextCompat.getColor(itemView.context, R.color.blue))
        }
        commentaryText.text = city.commentary

        weatherIcon.setImageDrawable(getDrawable(itemView.context, getImage(city.weather)))
        itemView.setOnClickListener { onItemClick(city) }
    }
}