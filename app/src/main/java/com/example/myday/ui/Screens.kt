package com.example.myday.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import coil.compose.AsyncImage
import com.example.myday.data.WeatherResult
import com.example.myday.ui.alarms.AlarmViewModel
import com.example.myday.ui.alarms.AlarmItem
import com.example.myday.ui.alarms.AddAlarmDialog
import com.example.myday.ui.routines.RoutineViewModel
import com.example.myday.ui.routines.RoutineItem
import com.example.myday.ui.routines.SparkleOverlay
import com.example.myday.ui.routines.AddRoutineDialog
import com.example.myday.ui.theme.MyDayTheme
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.platform.LocalContext
import com.example.myday.ui.ViewModelFactory

@Composable
fun CelestialClock(
    languageViewModel: LanguageViewModel,
    modifier: Modifier = Modifier
) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val isDay = hour in 6..17

    var time by remember { mutableStateOf(SimpleDateFormat("HH:mm", languageViewModel.getLocale()).format(Date())) }
    
    LaunchedEffect(Unit) {
        while(true) {
            kotlinx.coroutines.delay(1000)
            time = SimpleDateFormat("HH:mm", languageViewModel.getLocale()).format(Date())
        }
    }

    Box(
        modifier = modifier
            .size(240.dp)
            .shadow(16.dp, CircleShape)
            .clip(CircleShape)
            .background(
                Brush.verticalGradient(
                    if (isDay) listOf(Color(0xFFFFE082), Color(0xFFFFB300))
                    else listOf(Color(0xFF311B92), Color(0xFF1A237E))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = if (isDay) Icons.Rounded.WbSunny else Icons.Rounded.Bedtime,
            contentDescription = null,
            modifier = Modifier.size(160.dp),
            tint = (if (isDay) Color.White else Color(0xFFFFD54F)).copy(alpha = 0.2f)
        )
        
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = time,
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                ),
                color = Color.White
            )
            Text(
                text = if (isDay) "✨ " + languageViewModel.getString("home_greeting") else "🌙 " + languageViewModel.getString("home_greeting"),
                style = MaterialTheme.typography.labelMedium,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
        
        Text(
            text = if (isDay) "😊" else "✨",
            modifier = Modifier.align(Alignment.Center).padding(top = 80.dp),
            fontSize = 32.sp
        )
    }
}

@Composable
fun HomeScreen(
    languageViewModel: LanguageViewModel = viewModel(factory = ViewModelFactory(LocalContext.current)),
    weatherViewModel: WeatherViewModel = viewModel(factory = ViewModelFactory(LocalContext.current)),
    onNavigateToWeather: () -> Unit = {},
    onNavigateToCalendar: () -> Unit = {}
) {
    val locale = languageViewModel.getLocale()
    val formattedDate = remember(locale) {
        SimpleDateFormat("d 'de' MMMM", locale).format(Date()).lowercase()
    }

    AppBackground {
        Box(modifier = Modifier.fillMaxSize()) {
            FloatingDecoration(
                icon = Icons.Rounded.AutoAwesome,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 64.dp, end = 24.dp)
                    .size(32.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
            )

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = Color.Transparent,
                bottomBar = { Footer(languageViewModel) }
            ) { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Spacer(modifier = Modifier.height(32.dp))
                        CelestialClock(languageViewModel = languageViewModel)
                    }

                    item {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = languageViewModel.getString("hello_user"),
                                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = formattedDate.uppercase(),
                                style = MaterialTheme.typography.labelMedium.copy(letterSpacing = 2.sp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                            )
                        }
                    }

                    item {
                        MiniCalendarCard(
                            languageViewModel = languageViewModel,
                            onClick = onNavigateToCalendar
                        )
                    }

                    item {
                        WeatherSummaryCard(
                            weatherViewModel = weatherViewModel,
                            languageViewModel = languageViewModel,
                            onClick = onNavigateToWeather
                        )
                    }
                    
                    item {
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun MiniCalendarCard(
    languageViewModel: LanguageViewModel,
    onClick: () -> Unit
) {
    val calendar = Calendar.getInstance()
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, languageViewModel.getLocale()) ?: ""
    
    var time by remember { mutableStateOf(SimpleDateFormat("HH:mm", languageViewModel.getLocale()).format(Date())) }
    
    LaunchedEffect(Unit) {
        while(true) {
            kotlinx.coroutines.delay(1000)
            time = SimpleDateFormat("HH:mm", languageViewModel.getLocale()).format(Date())
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
    ) {
        Box {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(12.dp),
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
            )

            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Surface(
                    modifier = Modifier.size(72.dp),
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = day.toString(),
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = dayOfWeek.take(3).uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                        )
                    }
                }
                
                Column {
                    Text(
                        text = time,
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = languageViewModel.getString("open_calendar"),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherSummaryCard(
    weatherViewModel: WeatherViewModel,
    languageViewModel: LanguageViewModel,
    onClick: () -> Unit
) {
    val weatherState by weatherViewModel.weatherState.collectAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(12.dp),
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
            )

            when (weatherState) {
                is WeatherResult.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 2.dp
                    )
                }
                is WeatherResult.Success -> {
                    val data = (weatherState as WeatherResult.Success).data
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "${data.temperature.toInt()}°",
                                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = data.condition,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        
                        AsyncImage(
                            model = data.iconUrl,
                            contentDescription = data.condition,
                            modifier = Modifier.size(64.dp)
                        )
                    }
                }
                is WeatherResult.Error -> {
                    Text(
                        text = languageViewModel.getString("weather_error"),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun Footer(languageViewModel: LanguageViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = languageViewModel.getString("developed_by").uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 2.sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel = viewModel(factory = ViewModelFactory(LocalContext.current)),
    languageViewModel: LanguageViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))
) {
    val weatherState by weatherViewModel.weatherState.collectAsState()

    AppBackground {
        Box(modifier = Modifier.fillMaxSize()) {
            FloatingDecoration(
                icon = Icons.Rounded.FilterVintage,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 48.dp, start = 16.dp)
                    .size(28.dp),
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
            )

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = Color.Transparent,
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = languageViewModel.getString("check_weather").replace(" ☀️", ""),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Transparent
                        )
                    )
                },
                bottomBar = { Footer(languageViewModel) }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (weatherState) {
                        is WeatherResult.Loading -> {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        }
                        is WeatherResult.Success -> {
                            val data = (weatherState as WeatherResult.Success).data
                            
                            Spacer(modifier = Modifier.height(24.dp))
                            
                            AsyncImage(
                                model = data.iconUrl,
                                contentDescription = data.condition,
                                modifier = Modifier.size(140.dp)
                            )
                            
                            Text(
                                text = "${data.temperature.toInt()}${languageViewModel.getString("temp_unit")}",
                                style = MaterialTheme.typography.displayLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = (-2).sp
                                ),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            
                            Text(
                                text = data.condition.uppercase(),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    letterSpacing = 2.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                color = MaterialTheme.colorScheme.primary
                            )

                            Spacer(modifier = Modifier.height(48.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(MaterialTheme.shapes.large)
                                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
                                    .padding(20.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                WeatherDetailItem(
                                    icon = Icons.Rounded.Thermostat,
                                    label = languageViewModel.getString("feels_like"),
                                    value = "${data.temperature.toInt()}°"
                                )
                                VerticalDivider(modifier = Modifier.height(40.dp), color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                                WeatherDetailItem(
                                    icon = Icons.Rounded.WaterDrop,
                                    label = languageViewModel.getString("humidity"),
                                    value = "60%"
                                )
                                VerticalDivider(modifier = Modifier.height(40.dp), color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                                WeatherDetailItem(
                                    icon = Icons.Rounded.Air,
                                    label = languageViewModel.getString("wind"),
                                    value = "15 km/h"
                                )
                            }
                        }
                        is WeatherResult.Error -> {
                            Text(text = languageViewModel.getString("weather_error"), color = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherDetailItem(icon: ImageVector, label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
        Text(text = value, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
        Text(text = label.lowercase(), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun CalendarScreen(languageViewModel: LanguageViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))) {
    val calendar = Calendar.getInstance()
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
    val currentMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, languageViewModel.getLocale())
    val currentYear = calendar.get(Calendar.YEAR)

    AppBackground {
        Box(modifier = Modifier.fillMaxSize()) {
            FloatingDecoration(
                icon = Icons.Rounded.Favorite,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 100.dp, end = 24.dp)
                    .size(24.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            )

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = Color.Transparent,
                bottomBar = { Footer(languageViewModel) }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(24.dp)
                ) {
                    Text(
                        text = "$currentMonth $currentYear",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                                listOf("S", "M", "T", "W", "T", "F", "S").forEach { day ->
                                    Text(
                                        text = day,
                                        modifier = Modifier.width(40.dp),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            
                            val daysInMonth = 31
                            val firstDayOfWeek = 1
                            var dayCount = 1
                            
                            repeat(5) { week ->
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                                    repeat(7) { dayOfWeek ->
                                        val day = if (week == 0 && dayOfWeek < firstDayOfWeek) -1 else if (dayCount > daysInMonth) -1 else dayCount++
                                        
                                        Box(
                                            modifier = Modifier
                                                .size(40.dp)
                                                .clip(CircleShape)
                                                .background(if (day == currentDay) MaterialTheme.colorScheme.primary else Color.Transparent),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            if (day != -1) {
                                                Text(
                                                    text = day.toString(),
                                                    color = if (day == currentDay) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                                                    style = if (day == currentDay) MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.bodyMedium
                                                )
                                            }
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AlertsScreen(
    viewModel: AlarmViewModel = viewModel(factory = ViewModelFactory(LocalContext.current)),
    languageViewModel: LanguageViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))
) {
    val alarms by viewModel.alarms.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    AppBackground {
        Box(modifier = Modifier.fillMaxSize()) {
            FloatingDecoration(
                icon = Icons.Rounded.NotificationsActive,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 40.dp, end = 32.dp)
                    .size(30.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            )

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = Color.Transparent,
                bottomBar = { Footer(languageViewModel) },
                floatingActionButton = {
                    LargeFloatingActionButton(
                        onClick = { showAddDialog = true },
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        shape = CircleShape
                    ) {
                        Icon(Icons.Rounded.AddAlarm, contentDescription = languageViewModel.getString("add_alert"))
                    }
                }
            ) { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 32.dp)
                ) {
                    item {
                        Column(modifier = Modifier.padding(bottom = 24.dp)) {
                            Text(
                                text = languageViewModel.getString("my_alerts_title"),
                                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = "${alarms.size} ACTIVE",
                                style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 2.sp),
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                            )
                        }
                    }

                    if (alarms.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 64.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = languageViewModel.getString("alarms_empty"),
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    } else {
                        items(alarms, key = { it.id }) { alarm ->
                            AlarmItem(
                                alarm = alarm,
                                languageViewModel = languageViewModel,
                                onToggle = { viewModel.toggleAlarm(alarm) },
                                onDelete = { viewModel.deleteAlarm(alarm) }
                            )
                        }
                    }
                }
            }

            if (showAddDialog) {
                AddAlarmDialog(
                    languageViewModel = languageViewModel,
                    onDismiss = { showAddDialog = false },
                    onConfirm = { label, time, type, isWeekendOnly ->
                        viewModel.addAlarm(label, time, type, isWeekendOnly)
                        showAddDialog = false
                    }
                )
            }
        }
    }
}

@Composable
fun SettingsScreen(languageViewModel: LanguageViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))) {
    val currentLanguage by languageViewModel.currentLanguage.collectAsState()

    AppBackground {
        Box(modifier = Modifier.fillMaxSize()) {
            FloatingDecoration(
                icon = Icons.Rounded.FilterVintage,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 48.dp, start = 16.dp)
                    .size(28.dp),
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
            )

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = Color.Transparent,
                topBar = {
                    Box(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = languageViewModel.getString("settings_title"),
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                bottomBar = { Footer(languageViewModel) }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 24.dp)
                ) {
                    SettingsGroup(title = languageViewModel.getString("language_label").uppercase()) {
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
}

@Composable
fun SettingsGroup(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            content()
        }
    }
}

@Composable
fun LanguageOption(
    language: AppLanguage,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f) else Color.Transparent,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = language.label,
                style = MaterialTheme.typography.bodyLarge,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun RoutinesScreen(
    viewModel: RoutineViewModel = viewModel(factory = ViewModelFactory(LocalContext.current)),
    languageViewModel: LanguageViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))
) {
    val routines by viewModel.routines.collectAsState()
    val celebrationTrigger by viewModel.celebrationEvent.collectAsState(initial = Unit)
    var showSparkles by remember { mutableStateOf(false) }
    var showAddDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val wellDoneText = languageViewModel.getString("well_done")

    LaunchedEffect(celebrationTrigger) {
        if (celebrationTrigger != Unit) {
            showSparkles = true
            scope.launch {
                snackbarHostState.showSnackbar(wellDoneText)
            }
            kotlinx.coroutines.delay(2000)
            showSparkles = false
        }
    }

    val completedCount = routines.count { it.isCompleted }
    val totalCount = routines.size

    AppBackground {
        Box(modifier = Modifier.fillMaxSize()) {
            FloatingDecoration(
                icon = Icons.Rounded.AutoAwesome,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 40.dp, end = 32.dp)
                    .size(30.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            )

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = Color.Transparent,
                snackbarHost = { SnackbarHost(snackbarHostState) },
                bottomBar = { Footer(languageViewModel) },
                floatingActionButton = {
                    LargeFloatingActionButton(
                        onClick = { showAddDialog = true },
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        shape = CircleShape
                    ) {
                        Icon(Icons.Rounded.Add, contentDescription = languageViewModel.getString("add_routine"))
                    }
                }
            ) { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Column(modifier = Modifier.padding(bottom = 24.dp)) {
                            Text(
                                text = languageViewModel.getString("routines_title"),
                                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = "$completedCount/$totalCount ${languageViewModel.getString("progress_label")}",
                                style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 2.sp),
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                            )
                        }
                    }

                    if (routines.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 64.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Start your day with a routine! ✨",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    } else {
                        items(routines, key = { it.id }) { routine ->
                            RoutineItem(
                                routine = routine,
                                languageViewModel = languageViewModel,
                                onToggle = { viewModel.toggleRoutine(routine) },
                                onDelete = { viewModel.deleteRoutine(routine) }
                            )
                        }
                    }
                }
            }

            SparkleOverlay(visible = showSparkles)
            
            if (showAddDialog) {
                AddRoutineDialog(
                    languageViewModel = languageViewModel,
                    onDismiss = { showAddDialog = false },
                    onConfirm = { name, icon, isWeekendOnly ->
                        viewModel.addRoutine(name, icon, isWeekendOnly)
                        showAddDialog = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyDayTheme(darkTheme = false) {
        HomeScreen()
    }
}
