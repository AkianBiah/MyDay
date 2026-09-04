package com.example.myday.ui.alarms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myday.data.Alarm
import com.example.myday.data.AlarmType
import com.example.myday.ui.LanguageViewModel
import java.util.Calendar

@Composable
fun AlarmItem(
    alarm: Alarm,
    languageViewModel: LanguageViewModel,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = formatTo12h(alarm.time, languageViewModel),
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = if (alarm.isEnabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = when(alarm.type) {
                            AlarmType.Medication -> Icons.Rounded.MedicalServices
                            AlarmType.WakeUp -> Icons.Rounded.WbSunny
                            AlarmType.Routine -> Icons.Rounded.AutoAwesome
                        },
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = alarm.label,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = alarm.isEnabled,
                    onCheckedChange = { onToggle() },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
                IconButton(onClick = onDelete) {
                    Icon(
                        Icons.Rounded.DeleteOutline,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAlarmDialog(
    languageViewModel: LanguageViewModel,
    onDismiss: () -> Unit,
    onConfirm: (String, String, AlarmType, Boolean) -> Unit
) {
    var label by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(AlarmType.Routine) }
    var isWeekendOnly by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState(
        initialHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
        initialMinute = Calendar.getInstance().get(Calendar.MINUTE),
        is24Hour = false
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val formattedTime = String.format("%02d:%02d", timePickerState.hour, timePickerState.minute)
                onConfirm(label.ifBlank { languageViewModel.getString(selectedType.name.lowercase()) }, formattedTime, selectedType, isWeekendOnly)
            }) {
                Text(languageViewModel.getString("add_alert_btn"))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text(languageViewModel.getString("add_alert")) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                TimePicker(
                    state = timePickerState,
                    modifier = Modifier.fillMaxWidth()
                )
                
                TextField(
                    value = label,
                    onValueChange = { label = it },
                    label = { Text(languageViewModel.getString("alert_label")) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    AlarmType.values().forEach { type ->
                        FilterChip(
                            selected = selectedType == type,
                            onClick = { selectedType = type },
                            label = { Text(languageViewModel.getString(type.name.lowercase())) },
                            leadingIcon = if (selectedType == type) {
                                { Icon(Icons.Rounded.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                            } else null
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Weekend Only?", style = MaterialTheme.typography.bodyMedium)
                    Switch(
                        checked = isWeekendOnly,
                        onCheckedChange = { isWeekendOnly = it }
                    )
                }
            }
        },
        shape = MaterialTheme.shapes.extraLarge
    )
}

fun formatTo12h(time: String, languageViewModel: LanguageViewModel): String {
    return try {
        val sdf24 = java.text.SimpleDateFormat("HH:mm", java.util.Locale.US)
        val date = sdf24.parse(time) ?: return time
        val sdf12 = java.text.SimpleDateFormat("h:mm a", languageViewModel.getLocale())
        sdf12.format(date)
    } catch (e: Exception) {
        time
    }
}
