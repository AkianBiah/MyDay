package com.example.myday.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @Json(name = "main") val main: MainData,
    @Json(name = "weather") val weather: List<WeatherCondition>,
    @Json(name = "wind") val wind: WindData,
    @Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
data class MainData(
    @Json(name = "temp") val temp: Double,
    @Json(name = "feels_like") val feelsLike: Double,
    @Json(name = "humidity") val humidity: Int
)

@JsonClass(generateAdapter = true)
data class WindData(
    @Json(name = "speed") val speed: Double
)

@JsonClass(generateAdapter = true)
data class WeatherCondition(
    @Json(name = "main") val main: String,
    @Json(name = "description") val description: String,
    @Json(name = "icon") val icon: String
)

data class WeatherInfo(
    val temperature: Double,
    val feelsLike: Double,
    val humidity: Int,
    val windSpeed: Double,
    val condition: String,
    val iconUrl: String,
    val cityName: String
)
