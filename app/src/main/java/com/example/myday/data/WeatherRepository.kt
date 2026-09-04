package com.example.myday.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Repository to handle weather data.
 * 
 * To use a real weather API:
 * 1. Sign up at https://openweathermap.org/
 * 2. Get your API Key.
 * 3. Add it to `local.properties` as `OPENWEATHER_API_KEY=your_key_here`.
 * 4. Update this class to read the key or pass it via constructor.
 * 
 * Currently using mock data if no key is provided.
 */
class WeatherRepository(
    private val apiKey: String = "" // Should be provided via local.properties or injected
) {
    private val api: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }

    fun getWeather(cityName: String): Flow<WeatherResult> = flow {
        emit(WeatherResult.Loading)
        if (apiKey.isEmpty()) {
            // Mock data if no API key
            kotlinx.coroutines.delay(1000)
            emit(WeatherResult.Success(
                WeatherInfo(
                    temperature = 22.5,
                    feelsLike = 21.0,
                    humidity = 60,
                    windSpeed = 12.5,
                    condition = "Sunny",
                    iconUrl = "https://openweathermap.org/img/wn/01d@2x.png",
                    cityName = cityName
                )
            ))
        } else {
            try {
                val response = api.getCurrentWeatherByCity(cityName, apiKey)
                val info = WeatherInfo(
                    temperature = response.main.temp,
                    feelsLike = response.main.feelsLike,
                    humidity = response.main.humidity,
                    windSpeed = response.wind.speed,
                    condition = response.weather.firstOrNull()?.main ?: "Unknown",
                    iconUrl = "https://openweathermap.org/img/wn/${response.weather.firstOrNull()?.icon}@2x.png",
                    cityName = response.name
                )
                emit(WeatherResult.Success(info))
            } catch (e: Exception) {
                emit(WeatherResult.Error(e.message ?: "Unknown error"))
            }
        }
    }
}

sealed class WeatherResult {
    object Loading : WeatherResult()
    data class Success(val data: WeatherInfo) : WeatherResult()
    data class Error(val message: String) : WeatherResult()
}
