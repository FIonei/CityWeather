package com.example.cityweather.presentation.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cityweather.R
import com.example.cityweather.domain.City
import com.example.cityweather.getImage

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
        val id = intent.getLongExtra(EXTRA_ID, 0)
        DetailsViewModelFactory(id)
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
        weatherIcon = findViewById(R.id.weather_icon)
        backButton = findViewById(R.id.backButton)
    }

    private fun closeScreen(){
        finish()
    }
    }