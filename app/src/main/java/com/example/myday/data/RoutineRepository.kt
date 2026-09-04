package com.example.myday.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.util.Calendar

class RoutineRepository(private val routineDao: RoutineDao) {

    val allRoutines: Flow<List<Routine>> = routineDao.getAllRoutines()

    suspend fun checkAndResetRoutines() {
        val routines = routineDao.getAllRoutines().first()
        val now = Calendar.getInstance()
        val isWeekend = now.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || 
                        now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY

        routines.forEach { routine ->
            // Reset if it's a new day
            if (routine.isCompleted && !isSameDay(routine.lastCompletedDate, now.timeInMillis)) {
                routineDao.updateRoutine(routine.copy(isCompleted = false))
            }
        }
    }

    suspend fun toggleCompletion(routine: Routine) {
        val now = Calendar.getInstance().timeInMillis
        val updatedRoutine = if (!routine.isCompleted) {
            routine.copy(isCompleted = true, lastCompletedDate = now)
        } else {
            routine.copy(isCompleted = false)
        }
        routineDao.updateRoutine(updatedRoutine)
    }

    suspend fun insert(routine: Routine) {
        routineDao.insertRoutine(routine)
    }

    suspend fun delete(routine: Routine) {
        routineDao.deleteRoutine(routine)
    }

    private fun isSameDay(timestamp1: Long, timestamp2: Long): Boolean {
        if (timestamp1 == 0L) return false
        val cal1 = Calendar.getInstance().apply { timeInMillis = timestamp1 }
        val cal2 = Calendar.getInstance().apply { timeInMillis = timestamp2 }
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
    }
}
