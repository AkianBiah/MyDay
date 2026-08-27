package com.example.myday.data

import java.util.UUID

data class Task(
    val id: UUID = UUID.randomUUID(),
    val description: String,
    val isCompleted: Boolean = false
)
