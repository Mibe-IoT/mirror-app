package com.mibe.bm.wi.weather.service

import com.mibe.bm.wi.weather.model.WeatherData
import com.mibe.bm.wi.web.service.WebClientService

class WeatherServiceImpl(
    private val webClientService: WebClientService
) : WeatherService {

    override suspend fun getWeatherData(cityName: String, weatherApiKey: String, weatherApiUrl: String)
            : WeatherData {
        val url = weatherApiUrl.format(cityName, weatherApiKey)
//        println(url)
        return webClientService.requestForObject(url)
    }

}