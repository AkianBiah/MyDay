package com.example.myday.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myday.data.AppDatabase
import com.example.myday.data.RoutineRepository
import com.example.myday.ui.routines.RoutineViewModel

import com.example.myday.data.AlarmRepository
import com.example.myday.ui.alarms.AlarmViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RoutineViewModel::class.java) -> {
                val database = AppDatabase.getDatabase(context)
                val repository = RoutineRepository(database.routineDao())
                RoutineViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AlarmViewModel::class.java) -> {
                val database = AppDatabase.getDatabase(context)
                val repository = AlarmRepository(context, database.alarmDao())
                AlarmViewModel(repository) as T
            }
            modelClass.isAssignableFrom(WeatherViewModel::class.java) -> {
                WeatherViewModel() as T
            }
            modelClass.isAssignableFrom(LanguageViewModel::class.java) -> {
                LanguageViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
