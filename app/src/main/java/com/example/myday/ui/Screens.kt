package com.example.myday.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myday.ui.tasks.KawaiiAddTaskField
import com.example.myday.ui.tasks.KawaiiTaskItem
import com.example.myday.ui.tasks.TaskViewModel

import androidx.compose.ui.tooling.preview.Preview
import com.example.myday.ui.theme.MyDayTheme

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HomeScreen(
    weatherViewModel: WeatherViewModel = viewModel(),
    languageViewModel: LanguageViewModel = viewModel()
) {
    val weatherState by weatherViewModel.weatherState.collectAsState()
    val currentTime by weatherViewModel.currentTime.collectAsState()
    val currentLanguage by languageViewModel.currentLanguage.collectAsState()
    val locale = languageViewModel.getLocale()

    val formattedTime = remember(currentTime, locale) {
        SimpleDateFormat("HH:mm", locale).format(currentTime)
    }
    val formattedDay = remember(currentTime, locale) {
        SimpleDateFormat("EEEE", locale).format(currentTime).replaceFirstChar { it.uppercase() }
    }
    val formattedDate = remember(currentTime, locale) {
        SimpleDateFormat("d MMM", locale).format(currentTime)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f)
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            // Decorative stars
            Icon(
                Icons.Rounded.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                modifier = Modifier
                    .padding(32.dp)
                    .align(Alignment.TopStart)
            )
            Icon(
                Icons.Rounded.Favorite,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                modifier = Modifier
                    .padding(32.dp)
                    .align(Alignment.BottomEnd)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                KawaiiClock(
                    time = formattedTime,
                    dayOfWeek = formattedDay,
                    date = formattedDate,
                    languageViewModel = languageViewModel
                )
                Spacer(modifier = Modifier.height(16.dp))
                WeatherCard(weatherState = weatherState, languageViewModel = languageViewModel)

                Spacer(modifier = Modifier.weight(1f))
                
                Text(
                    text = languageViewModel.getString("home_greeting"),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }
        }
    }
}

@Composable
fun TasksScreen(
    viewModel: TaskViewModel = viewModel(),
    languageViewModel: LanguageViewModel = viewModel()
) {
    val tasks by viewModel.tasks.collectAsState()
    var newTaskDescription by remember { mutableStateOf("") }
    val currentLanguage by languageViewModel.currentLanguage.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.1f),
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = languageViewModel.getString("tasks_title"),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                KawaiiAddTaskField(
                    value = newTaskDescription,
                    onValueChange = { newTaskDescription = it },
                    placeholder = languageViewModel.getString("add_task_hint"),
                    onAdd = {
                        viewModel.addTask(newTaskDescription)
                        newTaskDescription = ""
                    }
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    if (tasks.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 64.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = languageViewModel.getString("tasks_empty"),
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                    items(tasks, key = { it.id }) { task ->
                        KawaiiTaskItem(
                            task = task,
                            onToggle = { viewModel.toggleTaskCompletion(task.id) },
                            onDelete = { viewModel.deleteTask(task.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsScreen(languageViewModel: LanguageViewModel = viewModel()) {
    val currentLanguage by languageViewModel.currentLanguage.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.1f)
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = languageViewModel.getString("settings_title"),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(32.dp))

                KawaiiSettingsGroup(title = languageViewModel.getString("language_label")) {
                    AppLanguage.entries.forEach { language ->
                        LanguageOption(
                            language = language,
                            isSelected = currentLanguage == language,
                            onClick = { languageViewModel.setLanguage(language) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun KawaiiSettingsGroup(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.large)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        content()
    }
}

@Composable
fun LanguageOption(
    language: AppLanguage,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
            )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = language.label,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
        if (isSelected) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                Icons.Rounded.Check,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true, name = "Home Screen Day")
@Composable
fun HomeScreenDayPreview() {
    MyDayTheme(darkTheme = false) {
        HomeScreen()
    }
}

@Preview(showBackground = true, name = "Home Screen Night")
@Composable
fun HomeScreenNightPreview() {
    MyDayTheme(darkTheme = true) {
        HomeScreen()
    }
}

@Preview(showBackground = true, name = "Tasks Screen Day")
@Composable
fun TasksScreenDayPreview() {
    MyDayTheme(darkTheme = false) {
        TasksScreen()
    }
}

