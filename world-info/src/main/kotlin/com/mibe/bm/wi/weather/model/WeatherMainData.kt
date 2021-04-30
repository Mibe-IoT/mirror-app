package com.mibe.bm.wi.weather.model

data class WeatherMainData(
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val humidity: Int,
    val pressure: Int
)
