package com.example.myday.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myday.data.WeatherRepository
import com.example.myday.data.WeatherResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WeatherViewModel(
    private val repository: WeatherRepository = WeatherRepository()
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherResult>(WeatherResult.Loading)
    val weatherState: StateFlow<WeatherResult> = _weatherState.asStateFlow()

    private val _currentTime = MutableStateFlow(Date())
    val currentTime: StateFlow<Date> = _currentTime.asStateFlow()
    
    val formattedTime: StateFlow<String> = _currentTime.map {
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val formattedDate: StateFlow<String> = _currentTime.map {
        SimpleDateFormat("EEEE, MMMM d", Locale.getDefault()).format(it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    init {
        fetchWeather("São Paulo") // Default city
        updateTime()
    }

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            repository.getWeather(city).collect {
                _weatherState.value = it
            }
        }
    }

    private fun updateTime() {
        viewModelScope.launch {
            while (true) {
                _currentTime.value = Date()
                delay(1000)
            }
        }
    }
}
