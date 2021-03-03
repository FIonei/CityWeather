package com.example.cityweather.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.widget.TextView
import com.example.cityweather.CityApplication
import com.example.cityweather.DetailsViewModel
import com.example.cityweather.R
import com.example.cityweather.dataClasses.City
import com.example.cityweather.getImage
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

    private val viewModel: DetailsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                modelClass
                    .getConstructor(CitiesRepository::class.java, Long::class.java)
                    .newInstance(
                        (application as CityApplication).cityRepository,
                        intent.getLongExtra(EXTRA_ID, 0)
                    )
        }
    }

    private lateinit var nameText: TextView
    private lateinit var temperatureText: TextView
    private lateinit var weatherIcon: ImageView
    private lateinit var commentaryInput: EditText
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        viewModel.city.observe(this, ::bindCity)
        viewModel.closeScreenEvent.observe(this) { closeScreen() }

        initViews()
    }

    fun bindCity(city: City) {
        nameText.text = getString(R.string.name_format, city.name)
        if (city.temperature > 0) temperatureText.text = getString(R.string.plus_format, city.temperature)
        else temperatureText.text = getString(R.string.zero_format, city.temperature)
        //commentaryInput.setText(city.commentary)
        weatherIcon.setImageDrawable(getDrawable(getImage(city.weather)))

        backButton.setOnClickListener {
            val editedCity = city.copy()
            viewModel.saveCity(editedCity)
        }
    }

    private fun initViews() {
        nameText = findViewById(R.id.name_text)
        temperatureText = findViewById(R.id.temperature_text)
        commentaryInput = findViewById(R.id.commentary_input)
        backButton = findViewById(R.id.backButton)
    }

    private fun closeScreen() {
        finish()
    }
}

    /*
    companion object {

        private const val EXTRA_ID = "EXTRA_ID"

        fun start(context: Context, id: Long) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            context.startActivity(intent)
        }
    }

    private val viewModel: DetailsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                modelClass
                    .getConstructor(CitiesRepository::class.java, Long::class.java)
                    .newInstance(
                        (application as CityApplication).cityRepository,
                        intent.getLongExtra(EXTRA_ID, 0)
                    )
        }
    }

    private lateinit var nameText: TextView
    private lateinit var temperatureText: TextView
    private lateinit var weatherIcon: ImageView
    private lateinit var commentaryInput: EditText
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        viewModel.city.observe(this, ::bindCity)
        viewModel.closeScreenEvent.observe(this) { closeScreen() }

        initViews()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun bindCity(city: City) {
        nameText.text = getString(R.string.name_format, city.name)
        if (city.temperature > 0) temperatureText.text = getString(R.string.plus_format, city.temperature)
        else temperatureText.text = getString(R.string.zero_format, city.temperature)
        commentaryInput.setText(city.commentary)
        weatherIcon.setImageDrawable(getDrawable(getImage(city.weather)))

        backButton.setOnClickListener {
             val updatedCity = city.copy(commentary = commentaryInput.text.toString())
             viewModel.saveCity(updatedCity)
        }
    }

    private fun initViews() {
        nameText = findViewById(R.id.name_text)
        temperatureText = findViewById(R.id.temperature_text)
        commentaryInput = findViewById(R.id.commentary_input)
        backButton = findViewById(R.id.backButton)
    }

    private fun closeScreen(){
        finish()
    }
    }*/