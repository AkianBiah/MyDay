package com.example.myday.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class AppLanguage(val code: String, val label: String) {
    PT("PT", "Português"),
    EN("EN", "English"),
    ES("ES", "Español")
}

class LanguageViewModel : ViewModel() {
    private val _currentLanguage = MutableStateFlow(AppLanguage.EN)
    val currentLanguage: StateFlow<AppLanguage> = _currentLanguage.asStateFlow()

    private val translations = mapOf(
        AppLanguage.EN to mapOf(
            "home_title" to "My Day ✨",
            "home_greeting" to "Have a magical day! ✨",
            "tasks_title" to "My Tasks ✨",
            "add_task_hint" to "Add a magical task...",
            "settings_title" to "Settings ✨",
            "language_label" to "Language",
            "tasks_empty" to "No tasks yet! Add one above ☁️",
            "calendar_title" to "Calendar",
            "weather_loading" to "Consulting the stars... 🌟",
            "weather_error" to "Clouds are blocking the view... ☁️",
            "temp_unit" to "°C"
        ),
        AppLanguage.PT to mapOf(
            "home_title" to "Meu Dia ✨",
            "home_greeting" to "Tenha um dia mágico! ✨",
            "tasks_title" to "Minhas Tarefas ✨",
            "add_task_hint" to "Adicione uma tarefa mágica...",
            "settings_title" to "Configurações ✨",
            "language_label" to "Idioma",
            "tasks_empty" to "Sem tarefas ainda! Adicione uma acima ☁️",
            "calendar_title" to "Calendário",
            "weather_loading" to "Consultando as estrelas... 🌟",
            "weather_error" to "Nuvens estão bloqueando a visão... ☁️",
            "temp_unit" to "°C"
        ),
        AppLanguage.ES to mapOf(
            "home_title" to "Mi Día ✨",
            "home_greeting" to "¡Que tengas un día mágico! ✨",
            "tasks_title" to "Mis Tareas ✨",
            "add_task_hint" to "Añade una tarea mágica...",
            "settings_title" to "Ajustes ✨",
            "language_label" to "Idioma",
            "tasks_empty" to "¡No hay tareas todavía! Añade una arriba ☁️",
            "calendar_title" to "Calendario",
            "weather_loading" to "Consultando las estrellas... 🌟",
            "weather_error" to "Las nubes bloquean la vista... ☁️",
            "temp_unit" to "°C"
        )
    )

    fun setLanguage(language: AppLanguage) {
        _currentLanguage.value = language
    }

    fun getString(key: String): String {
        return translations[_currentLanguage.value]?.get(key) ?: key
    }
}
