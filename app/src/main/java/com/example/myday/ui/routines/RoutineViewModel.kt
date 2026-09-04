package com.example.myday.ui.routines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myday.data.Routine
import com.example.myday.data.RoutineRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RoutineViewModel(private val repository: RoutineRepository) : ViewModel() {

    val routines: StateFlow<List<Routine>> = repository.allRoutines
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _celebrationEvent = MutableSharedFlow<Unit>()
    val celebrationEvent: SharedFlow<Unit> = _celebrationEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            repository.checkAndResetRoutines()
        }
    }

    fun addRoutine(name: String, iconName: String, isWeekendOnly: Boolean = false) {
        if (name.isBlank()) return
        viewModelScope.launch {
            repository.insert(Routine(name = name, iconName = iconName, isWeekendOnly = isWeekendOnly))
        }
    }

    fun toggleRoutine(routine: Routine) {
        viewModelScope.launch {
            val wasCompleted = routine.isCompleted
            repository.toggleCompletion(routine)
            if (!wasCompleted) {
                _celebrationEvent.emit(Unit)
            }
        }
    }

    fun deleteRoutine(routine: Routine) {
        viewModelScope.launch {
            repository.delete(routine)
        }
    }
}
