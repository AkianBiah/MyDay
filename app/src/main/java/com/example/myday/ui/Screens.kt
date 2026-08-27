package com.example.myday.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myday.ui.tasks.KawaiiAddTaskField
import com.example.myday.ui.tasks.KawaiiTaskItem
import com.example.myday.ui.tasks.TaskViewModel

import androidx.compose.ui.tooling.preview.Preview
import com.example.myday.ui.theme.MyDayTheme

@Composable
fun HomeScreen(viewModel: WeatherViewModel = viewModel()) {
    val weatherState by viewModel.weatherState.collectAsState()
    val formattedTime by viewModel.formattedTime.collectAsState()
    val formattedDate by viewModel.formattedDate.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KawaiiCalendar(date = formattedDate)
            Spacer(modifier = Modifier.height(8.dp))
            KawaiiClock(time = formattedTime)
            Spacer(modifier = Modifier.height(16.dp))
            WeatherCard(weatherState = weatherState)

            Spacer(modifier = Modifier.weight(1f))
            
            Text(
                text = "Have a magical day! ✨",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }
    }
}

@Composable
fun TasksScreen(viewModel: TaskViewModel = viewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    var newTaskDescription by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(modifier = Modifier.padding(16.dp)) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "My Tasks ✨",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            KawaiiAddTaskField(
                value = newTaskDescription,
                onValueChange = { newTaskDescription = it },
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

@Composable
fun SettingsScreen() {
    ScreenShell(title = "Settings")
}

@Composable
private fun ScreenShell(title: String) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
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

