package com.example.cityweather.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.cityweather.CityApplication
import com.example.cityweather.R
import com.example.cityweather.repositories.CitiesRepository

class DetailsActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_ID = "EXTRA_ID"

        fun start(context: Context, id: Long) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            context.startActivity(intent)
        }
    }

    private lateinit var citiesRepository: CitiesRepository

    private lateinit var nameText: TextView
    private lateinit var temperatureText: TextView
    private lateinit var commentaryInput: EditText
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        citiesRepository = (application as CityApplication).cityRepository

        nameText = findViewById(R.id.name_text)
        temperatureText = findViewById(R.id.temperature_text)
        commentaryInput = findViewById(R.id.commentary_input)
        backButton = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            val id = intent.getLongExtra(EXTRA_ID, 0)
            val city = citiesRepository.getCity(id)
            if (city != null) {
                val updatedCity = city.copy(commentary = commentaryInput.text.toString())
                citiesRepository.setCity(updatedCity)
            }
            finish()
        }

        initCity()
    }

    private fun initCity() {
        val id = intent.getLongExtra(EXTRA_ID, 0)
        val city = citiesRepository.getCity(id)

        if (city != null) {
            nameText.text = getString(R.string.name_format, city.name)
            if (city.temperature > 0) temperatureText.text = getString(R.string.plus_format, city.temperature)
            else temperatureText.text = getString(R.string.zero_format, city.temperature)
            commentaryInput.setText(city.commentary)
        } else {
            finish()
        }
    }
}