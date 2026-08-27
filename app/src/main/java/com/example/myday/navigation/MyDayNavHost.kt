package com.example.myday.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
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
import com.example.myday.ui.TasksScreen
import kotlinx.serialization.Serializable

@Serializable
sealed interface MyDayRoute : NavKey {
    @Serializable
    data object Home : MyDayRoute
    @Serializable
    data object Tasks : MyDayRoute
    @Serializable
    data object Settings : MyDayRoute
}

@Composable
fun MyDayNavHost() {
    val backStack = remember { mutableStateListOf<MyDayRoute>(MyDayRoute.Home) }
    val currentKey = backStack.lastOrNull() ?: MyDayRoute.Home

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            item(
                selected = currentKey is MyDayRoute.Home,
                onClick = {
                    if (currentKey !is MyDayRoute.Home) {
                        // For a simple bottom-nav like behavior
                        backStack.clear()
                        backStack.add(MyDayRoute.Home)
                    }
                },
                icon = { Icon(Icons.Rounded.Home, contentDescription = "Home") },
                label = { Text("Home") }
            )
            item(
                selected = currentKey is MyDayRoute.Tasks,
                onClick = {
                    if (currentKey !is MyDayRoute.Tasks) {
                        if (backStack.contains(MyDayRoute.Tasks)) {
                            backStack.remove(MyDayRoute.Tasks)
                        }
                        backStack.add(MyDayRoute.Tasks)
                    }
                },
                icon = { Icon(Icons.AutoMirrored.Rounded.List, contentDescription = "Tasks") },
                label = { Text("Tasks") }
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
                icon = { Icon(Icons.Rounded.Settings, contentDescription = "Settings") },
                label = { Text("Settings") }
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
                    is MyDayRoute.Home -> NavEntry(key) { HomeScreen() }
                    is MyDayRoute.Tasks -> NavEntry(key) { TasksScreen() }
                    is MyDayRoute.Settings -> NavEntry(key) { SettingsScreen() }
                }
            }
        )
    }
}
