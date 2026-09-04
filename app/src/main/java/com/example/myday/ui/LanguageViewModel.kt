package com.example.myday.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

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
            "home_title" to "Home",
            "home_greeting" to "Have a magical day! ✨",
            "settings_title" to "Settings",
            "language_label" to "Language",
            "calendar_title" to "Calendar",
            "weather_loading" to "Consulting the stars... 🌟",
            "weather_error" to "Clouds are blocking the view... ☁️",
            "temp_unit" to "°C",
            "hello_user" to "Welcome! 🌷",
            "add_alert_btn" to "Add Alert ✨",
            "open_calendar" to "Open Calendar 📅",
            "check_weather" to "Check Weather ☀️",
            "feels_like" to "Feels like",
            "humidity" to "Humidity",
            "wind" to "Wind",
            "next_days" to "Next Days",
            "developed_by" to "Developed by Bianca 💜",
            "routines_title" to "Routines",
            "add_routine" to "Add Routine",
            "routine_name" to "Name",
            "select_icon" to "Select Icon",
            "well_done" to "Well Done! ✨",
            "progress_label" to "completed",
            "alerts_title" to "Alerts",
            "my_alerts_title" to "My Alerts",
            "add_alert" to "Add Alert",
            "medication" to "Medication",
            "wake_up" to "Wake Up",
            "routine" to "Routine",
            "alert_label" to "Label",
            "alert_time" to "Time",
            "alert_type" to "Type",
            "alarms_empty" to "No alerts yet! ☁️",
            "daily" to "Daily",
            "weekend" to "Weekend"
        ),
        AppLanguage.PT to mapOf(
            "home_title" to "Início",
            "home_greeting" to "Tenha um dia mágico! ✨",
            "settings_title" to "Configurações",
            "language_label" to "Idioma",
            "calendar_title" to "Calendário",
            "weather_loading" to "Consultando as estrelas... 🌟",
            "weather_error" to "Nuvens estão bloqueando a visão... ☁️",
            "temp_unit" to "°C",
            "hello_user" to "Bem-vindo(a)! 🌷",
            "add_alert_btn" to "Adicionar Alerta ✨",
            "open_calendar" to "Abrir Calendário 📅",
            "check_weather" to "Ver Clima ☀️",
            "feels_like" to "Sensação",
            "humidity" to "Umidade",
            "wind" to "Vento",
            "next_days" to "Próximos Dias",
            "developed_by" to "Desenvolvido por Bianca 💜",
            "routines_title" to "Rotinas",
            "add_routine" to "Adicionar Rotina",
            "routine_name" to "Nome",
            "select_icon" to "Selecionar Ícone",
            "well_done" to "Muito Bem! ✨",
            "progress_label" to "concluídas",
            "alerts_title" to "Alertas",
            "my_alerts_title" to "Meus Alertas",
            "add_alert" to "Adicionar Alerta",
            "medication" to "Medicamento",
            "wake_up" to "Acordar",
            "routine" to "Rotina",
            "alert_label" to "Rótulo",
            "alert_time" to "Hora",
            "alert_type" to "Tipo",
            "alarms_empty" to "Sem alertas ainda! ☁️",
            "daily" to "Diário",
            "weekend" to "Fim de Semana"
        ),
        AppLanguage.ES to mapOf(
            "home_title" to "Inicio",
            "home_greeting" to "¡Que tengas un día mágico! ✨",
            "settings_title" to "Ajustes",
            "language_label" to "Idioma",
            "calendar_title" to "Calendario",
            "weather_loading" to "Consultando las estrellas... 🌟",
            "weather_error" to "Las nubes bloquean la vista... ☁️",
            "temp_unit" to "°C",
            "hello_user" to "¡Bienvenido(a)! 🌷",
            "add_alert_btn" to "Añadir Alerta ✨",
            "open_calendar" to "Abrir Calendario 📅",
            "check_weather" to "Ver Clima ☀️",
            "feels_like" to "Sensación",
            "humidity" to "Humedad",
            "wind" to "Viento",
            "next_days" to "Próximos Días",
            "developed_by" to "Desarrollado por Bianca 💜",
            "routines_title" to "Rutinas",
            "add_routine" to "Añadir Rutina",
            "routine_name" to "Nome",
            "select_icon" to "Seleccionar Icono",
            "well_done" to "¡Muy bien! ✨",
            "progress_label" to "completadas",
            "alerts_title" to "Alertas",
            "my_alerts_title" to "Mis Alertas",
            "add_alert" to "Añadir Alerta",
            "medication" to "Medicamento",
            "wake_up" to "Despertar",
            "routine" to "Rutina",
            "alert_label" to "Etiqueta",
            "alert_time" to "Hora",
            "alert_type" to "Tipo",
            "alarms_empty" to "¡No hay alertas todavía! ☁️",
            "daily" to "Diario",
            "weekend" to "Fin de Semana"
        )
    )

    fun setLanguage(language: AppLanguage) {
        _currentLanguage.value = language
    }

    fun getString(key: String): String {
        return translations[_currentLanguage.value]?.get(key) ?: key
    }

    fun getLocale(): Locale {
        return when (_currentLanguage.value) {
            AppLanguage.PT -> Locale.forLanguageTag("pt-BR")
            AppLanguage.ES -> Locale.forLanguageTag("es-ES")
            else -> Locale.ENGLISH
        }
    }
}
