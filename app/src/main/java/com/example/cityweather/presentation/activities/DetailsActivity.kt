package com.example.cityweather.presentation.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.cityweather.*
import com.example.cityweather.domain.City
import com.example.cityweather.presentation.presenters.DetailPresenter

class DetailsActivity : AppCompatActivity(), DetailsView {

    companion object {

        private const val EXTRA_ID = "EXTRA_ID"

        fun start(context: Context, id: Long) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            context.startActivity(intent)
        }
    }

    private val presenter by lazy{
        DetailPresenter(
            repository = (application as CityApplication).cityRepository,
            personId = intent.getLongExtra(EXTRA_ID, 0)
        )
    }

    private lateinit var nameText: TextView
    private lateinit var temperatureText: TextView
    private lateinit var weatherIcon: ImageView
    private lateinit var commentaryInput: EditText
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initViews()
        presenter.attachView(this)
    }

    private fun initViews() {
        nameText = findViewById(R.id.name_text)
        temperatureText = findViewById(R.id.temperature_text)
        weatherIcon = findViewById(R.id.weather_icon)
        commentaryInput = findViewById(R.id.commentary_input)
        backButton = findViewById(R.id.backButton)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun bindCity(city: City){
            nameText.text = getString(R.string.name_format, city.name)
            if (city.temperature > 0) temperatureText.text = getString(R.string.plus_format, city.temperature)
            else temperatureText.text = getString(R.string.zero_format, city.temperature)
            commentaryInput.setText(city.commentary)
            weatherIcon.setImageDrawable(getDrawable(getImage(city.weather)))

        backButton.setOnClickListener{
            val editedCity = city.copy(commentary = commentaryInput.text.toString())
            presenter.saveCity(editedCity)
        }
    }

    override fun closeScreen(){
        finish()
    }

}