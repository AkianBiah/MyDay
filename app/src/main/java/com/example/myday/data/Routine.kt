package com.example.myday.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routines")
data class Routine(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val iconName: String,
    val isCompleted: Boolean = false,
    val lastCompletedDate: Long = 0L
)
