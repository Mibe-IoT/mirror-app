package com.mibe.bm.app.panel

import com.mibe.bm.app.component.JMultilineLabel
import com.mibe.bm.app.service.MessageService
import com.mibe.bm.wi.weather.controller.WeatherController
import com.mibe.bm.wi.weather.model.WeatherData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.swing.BoxLayout

class WeatherPanel(
    private val messageService: MessageService,
    private val weatherController: WeatherController
) : AppPanel() {

    private val panelTitle = AppPanelType.WEATHER.asciiArt
    private val title: JMultilineLabel = creteTitleLabel(panelTitle)
    private var weatherData: WeatherData? = null

    init {

        loadWeather()
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        add(title)
        isVisible = true
    }

    override fun onAction() {
        loadWeather()
    }

    override fun onUpdate() {
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
        val labelWeatherDescription = createJMultilineLabel(weatherData.weather[0].description)
        val temp = createJMultilineLabel(weatherData.main.temp.toString())
        add(labelWeatherMain)
        add(labelWeatherDescription)
        add(temp)
    }

}