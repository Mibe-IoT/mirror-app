package com.mibe.bm.wi.weather.controller

import com.mibe.bm.wi.files.FilePathService
import com.mibe.bm.wi.weather.model.WeatherData
import com.mibe.bm.wi.weather.service.WeatherService
import com.mibe.bm.wi.weather.service.WeatherServiceImpl
import com.mibe.bm.wi.web.service.WebClientService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class WeatherController(
    pathService: FilePathService,
) {

    private val webClientService: WebClientService
    private val weatherService: WeatherService

    private val propertiesPath = "web.properties"
    private var weatherApiUrl: String
    private var weatherApiKey: String
    private var cityName: String = "Minsk"

    init {
//        val properties = Properties()
        val props = ResourceBundle.getBundle("web")
        webClientService = WebClientService(props.getString("api.ip-resolve.url"))
        weatherService = WeatherServiceImpl(webClientService)
        weatherApiKey = props.getString("api.weather.key")
        weatherApiUrl = props.getString("api.weather.url")
        GlobalScope.launch {
            cityName = webClientService.getIpInfo().city
        }
    }

    suspend fun getWeatherData(): WeatherData {
        return weatherService.getWeatherData(cityName, weatherApiKey, weatherApiUrl)
    }

    fun reloadData() {
        val props = ResourceBundle.getBundle("web")
        weatherApiKey = props.getString("api.weather.key")
        weatherApiUrl = props.getString("api.weather.url")
        GlobalScope.launch {
            cityName = webClientService.getIpInfo().city
        }
    }

}