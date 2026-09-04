package com.example.myday.ui.alarms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myday.data.Alarm
import com.example.myday.data.AlarmRepository
import com.example.myday.data.AlarmType
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AlarmViewModel(private val repository: AlarmRepository) : ViewModel() {
    val alarms: StateFlow<List<Alarm>> = repository.allAlarms
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addAlarm(label: String, time: String, type: AlarmType) {
        viewModelScope.launch {
            repository.insert(Alarm(label = label, time = time, type = type))
        }
    }

    fun toggleAlarm(id: Int) {
        viewModelScope.launch {
            val alarm = alarms.value.find { it.id == id } ?: return@launch
            repository.update(alarm.copy(isEnabled = !alarm.isEnabled))
        }
    }

    fun deleteAlarm(id: Int) {
        viewModelScope.launch {
            val alarm = alarms.value.find { it.id == id } ?: return@launch
            repository.delete(alarm)
        }
    }
}
