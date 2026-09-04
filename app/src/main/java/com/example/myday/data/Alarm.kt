package com.example.myday.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val label: String,
    val time: String, // format "HH:mm"
    val isEnabled: Boolean = true,
    val type: AlarmType = AlarmType.Routine,
    val isWeekendOnly: Boolean = false
)

enum class AlarmType {
    Medication, WakeUp, Routine
}
