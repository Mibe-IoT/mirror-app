package com.mibe.bm.app.panel

import com.mibe.bm.app.component.JMultilineLabel
import com.mibe.bm.app.service.MessageService
import com.mibe.bm.app.theme.WeatherIcons
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
        onUpdate()
    }

    override fun onUpdate() {
        loadWeather()
    }

    private fun redraw() {
        removeAll()
        add(title)
        weatherData?.let { addWeatherData(it) }
            ?: add(createJMultilineLabel(messageService.getMessage("error.no_internet_connection")))
        validate()
    }

    private fun loadWeather() {
        GlobalScope.launch {
            weatherData = weatherController.getWeatherData()
        }.invokeOnCompletion {
//            redraw()
        }
    }

    private fun addWeatherData(weatherData: WeatherData) {
        val weatherMain =
            "${messageService.getMessage("weather.main")}: ${weatherData.weather[0].main}"

        val weatherDescription =
            "${messageService.getMessage("weather.description")}: ${weatherData.weather[0].description}"

        val temp =
            "${messageService.getMessage("weather.temp")}: ${weatherData.main.temp}. " +
                    "${messageService.getMessage("weather.feelsLike")} ${weatherData.main.feelsLike}"
        val minMaxTemp = "${messageService.getMessage("weather.maxTemp")}: ${weatherData.main.tempMax}, " +
                "${messageService.getMessage("weather.minTemp")}: ${weatherData.main.tempMin}"

        val humidityAndPressure =
            "${messageService.getMessage("weather.humidity")}: ${weatherData.main.humidity}. " +
                    "${messageService.getMessage("weather.pressure")}: ${weatherData.main.pressure}"

        val wind =
            "${messageService.getMessage("weather.wind.speed")}: ${weatherData.wind.speed}, " +
                    "${messageService.getMessage("weather.wind.deg")}: ${weatherData.wind.deg}"

        val location =
            "${messageService.getMessage("weather.location")}: ${weatherData.name}"
        add(
            createJMultilineLabel(
                listOf(
                    weatherMain,
                    weatherDescription,
                    "",
                    temp,
                    minMaxTemp,
                    humidityAndPressure,
                    wind,
                    "",
                    location
                ).joinToString(System.lineSeparator())
            )
        )
        add(createJMultilineLabel(getWeatherIcon(weatherData.weather[0].id)))
    }

    private fun getWeatherIcon(code: Int): String {
        return when (code.toString()[0]) {
            '2' -> WeatherIcons.THUNDERSTORM
            '3' -> WeatherIcons.DRIZZLE
            '5' -> WeatherIcons.RAIN
            '6' -> WeatherIcons.SNOW
            '7' -> WeatherIcons.ATMOSPHERE
            else -> if (code == 800) {
                WeatherIcons.CLEAR
            } else {
                WeatherIcons.CLOUDS
            }
        }.asciiArt
    }

}