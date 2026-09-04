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
    val isWeekendOnly: Boolean = false,
    val type: AlarmType = AlarmType.Routine
)

enum class AlarmType {
    Medication, WakeUp, Routine
}
