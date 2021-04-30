package com.mibe.bm.app.panel

import com.mibe.bm.app.component.JMultilineLabel
import com.mibe.bm.wi.weather.controller.WeatherController
import com.mibe.bm.wi.weather.model.WeatherData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherPanel(
    private val weatherController: WeatherController
) : VerticalAppPanel() {

    private val panelTitle = AppPanelType.WEATHER.asciiArt
    private val title: JMultilineLabel = creteTitleLabel(panelTitle)
    private var weatherData: WeatherData? = null

    init {
        add(title)
        loadWeather()
        isVisible = true
    }

    override fun onAction() {
        loadWeather()
    }

    private fun redraw() {
        removeAll()
        add(title)
        weatherData?.let { addWeatherData(it) } ?: add(createJMultilineLabel("No internet connection"))

        validate()
    }

    private fun loadWeather() {
        GlobalScope.launch {
            weatherData = weatherController.getWeatherData()
            println(weatherData)
        }.invokeOnCompletion {
            redraw()
        }
    }

    private fun addWeatherData(weatherData: WeatherData) {
        val labelWeatherMain = createJMultilineLabel(weatherData.weather[0].main)
        val labelWeatherDescription = createJMultilineLabel(weatherData.weather[0].main)
        val temp = createJMultilineLabel(weatherData.main.temp.toString())
        add(labelWeatherMain)
        add(labelWeatherDescription)
        add(temp)
    }

}