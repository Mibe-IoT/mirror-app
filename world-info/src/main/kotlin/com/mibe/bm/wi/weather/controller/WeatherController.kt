package com.mibe.bm.wi.weather.controller

import com.mibe.bm.wi.files.FilePathService
import com.mibe.bm.wi.weather.model.WeatherData
import com.mibe.bm.wi.weather.service.WeatherService
import com.mibe.bm.wi.web.service.WebClientService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.util.*

class WeatherController(
    private val pathService: FilePathService,
    private val weatherService: WeatherService,
    private val webClientService: WebClientService
) {

    private val propertiesPath = pathService.getFilePath("web.properties")
    private lateinit var weatherApiUrl: String
    private lateinit var weatherApiKey: String
    private var cityName: String = "Minsk"

    init {
        reloadData()
    }

    suspend fun getWeatherData(): WeatherData {
        return weatherService.getWeatherData(cityName, weatherApiKey, weatherApiUrl)
    }

    fun reloadData() {
        val properties = Properties()
        properties.load(File(propertiesPath).inputStream())
        weatherApiKey = properties.getProperty("api.weather.key")
        weatherApiUrl = properties.getProperty("api.weather.url")
        GlobalScope.launch {
            cityName = webClientService.getIpInfo().city
        }
    }

}