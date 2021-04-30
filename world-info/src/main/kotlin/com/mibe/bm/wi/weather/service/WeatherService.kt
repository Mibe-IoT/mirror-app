package com.mibe.bm.wi.weather.service

import com.mibe.bm.wi.weather.model.WeatherData

interface WeatherService {
    suspend fun getWeatherData(cityName: String, weatherApiKey: String, weatherApiUrl: String): WeatherData
}