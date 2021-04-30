package com.mibe.bm.wi.weather.model

data class WeatherData(
    val weather: List<Weather>,
    val main: WeatherMainData
)
