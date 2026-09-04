package com.example.myday.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.myday.ui.HomeScreen
import com.example.myday.ui.SettingsScreen
import com.example.myday.ui.AlertsScreen
import com.example.myday.ui.WeatherScreen
import com.example.myday.ui.CalendarScreen
import com.example.myday.ui.RoutinesScreen
import com.example.myday.ui.routines.RoutineViewModel
import com.example.myday.ui.alarms.AlarmViewModel
import com.example.myday.data.RoutineRepository
import com.example.myday.data.AlarmRepository
import kotlinx.serialization.Serializable

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.myday.ui.LanguageViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Serializable
sealed interface MyDayRoute : NavKey {
    @Serializable
    data object Home : MyDayRoute
    @Serializable
    data object Alerts : MyDayRoute
    @Serializable
    data object Routines : MyDayRoute
    @Serializable
    data object Settings : MyDayRoute
    @Serializable
    data object Weather : MyDayRoute
    @Serializable
    data object Calendar : MyDayRoute
}

@Composable
fun MyDayNavHost(
    routineRepository: RoutineRepository,
    alarmRepository: AlarmRepository,
    languageViewModel: LanguageViewModel = viewModel()
) {
    val backStack = remember { mutableStateListOf<MyDayRoute>(MyDayRoute.Home) }
    val currentKey = backStack.lastOrNull() ?: MyDayRoute.Home
    val currentLanguage by languageViewModel.currentLanguage.collectAsState()

    NavigationSuiteScaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        navigationSuiteItems = {
            item(
                selected = currentKey is MyDayRoute.Home,
                onClick = {
                    if (currentKey !is MyDayRoute.Home) {
                        backStack.clear()
                        backStack.add(MyDayRoute.Home)
                    }
                },
                icon = { Icon(Icons.Rounded.Home, contentDescription = languageViewModel.getString("home_title")) },
                label = { Text(languageViewModel.getString("home_title")) }
            )
            item(
                selected = currentKey is MyDayRoute.Alerts,
                onClick = {
                    if (currentKey !is MyDayRoute.Alerts) {
                        if (backStack.contains(MyDayRoute.Alerts)) {
                            backStack.remove(MyDayRoute.Alerts)
                        }
                        backStack.add(MyDayRoute.Alerts)
                    }
                },
                icon = { Icon(Icons.Rounded.NotificationsActive, contentDescription = languageViewModel.getString("alerts_title")) },
                label = { Text(languageViewModel.getString("alerts_title")) }
            )
            item(
                selected = currentKey is MyDayRoute.Routines,
                onClick = {
                    if (currentKey !is MyDayRoute.Routines) {
                        if (backStack.contains(MyDayRoute.Routines)) {
                            backStack.remove(MyDayRoute.Routines)
                        }
                        backStack.add(MyDayRoute.Routines)
                    }
                },
                icon = { Icon(Icons.Rounded.AutoAwesome, contentDescription = languageViewModel.getString("routines_title")) },
                label = { Text(languageViewModel.getString("routines_title")) }
            )
            item(
                selected = currentKey is MyDayRoute.Settings,
                onClick = {
                    if (currentKey !is MyDayRoute.Settings) {
                        if (backStack.contains(MyDayRoute.Settings)) {
                            backStack.remove(MyDayRoute.Settings)
                        }
                        backStack.add(MyDayRoute.Settings)
                    }
                },
                icon = { Icon(Icons.Rounded.Settings, contentDescription = languageViewModel.getString("settings_title")) },
                label = { Text(languageViewModel.getString("settings_title")) }
            )
        }
    ) {
        NavDisplay(
            backStack = backStack,
            onBack = { if (backStack.size > 1) backStack.removeAt(backStack.size - 1) },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = { key ->
                when (key) {
                    is MyDayRoute.Home -> NavEntry(key) { 
                        HomeScreen(
                            languageViewModel = languageViewModel,
                            onNavigateToWeather = { backStack.add(MyDayRoute.Weather) },
                            onNavigateToCalendar = { backStack.add(MyDayRoute.Calendar) }
                        ) 
                    }
                    is MyDayRoute.Alerts -> NavEntry(key) {
                        val alarmViewModel: AlarmViewModel = viewModel(
                            factory = object : androidx.lifecycle.ViewModelProvider.Factory {
                                override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                                    return AlarmViewModel(alarmRepository) as T
                                }
                            }
                        )
                        AlertsScreen(viewModel = alarmViewModel, languageViewModel = languageViewModel)
                    }
                    is MyDayRoute.Routines -> NavEntry(key) {
                        val routineViewModel: RoutineViewModel = viewModel(
                            factory = object : androidx.lifecycle.ViewModelProvider.Factory {
                                override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                                    return RoutineViewModel(routineRepository) as T
                                }
                            }
                        )
                        RoutinesScreen(viewModel = routineViewModel, languageViewModel = languageViewModel)
                    }
                    is MyDayRoute.Settings -> NavEntry(key) { SettingsScreen(languageViewModel = languageViewModel) }
                    is MyDayRoute.Weather -> NavEntry(key) { WeatherScreen(languageViewModel = languageViewModel) }
                    is MyDayRoute.Calendar -> NavEntry(key) { CalendarScreen(languageViewModel = languageViewModel) }
                }
            }
        )
    }
}
