package com.example.myday.ui.tasks

import androidx.lifecycle.ViewModel
import com.example.myday.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun addTask(description: String) {
        if (description.isBlank()) return
        _tasks.update { it + Task(description = description) }
    }

    fun toggleTaskCompletion(taskId: UUID) {
        _tasks.update { list ->
            list.map { task ->
                if (task.id == taskId) {
                    task.copy(isCompleted = !task.isCompleted)
                } else {
                    task
                }
            }
        }
    }

    fun deleteTask(taskId: UUID) {
        _tasks.update { list ->
            list.filter { it.id != taskId }
        }
    }
}
