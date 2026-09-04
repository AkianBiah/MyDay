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

    fun addTask(name: String) {
        if (name.isBlank()) return
        viewModelScope.launch {
            repository.insert(Routine(name = name, iconName = "Star"))
        }
    }

    fun toggleTaskCompletion(id: Int) {
        viewModelScope.launch {
            val routine = routines.value.find { it.id == id } ?: return@launch
            val wasCompleted = routine.isCompleted
            repository.toggleCompletion(routine)
            if (!wasCompleted) {
                _celebrationEvent.emit(Unit)
            }
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            val routine = routines.value.find { it.id == id } ?: return@launch
            repository.delete(routine)
        }
    }
}
