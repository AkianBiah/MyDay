# Implementation Plan - Celestial Clock and Alarm System

Implement Task 5 of the 'My Day - Celestial Alarm' project, replacing the legacy Task system with a new Alarm system and adding an interactive Celestial Clock to the Home screen.

## User Review Required

> [!IMPORTANT]
> The "Minhas Tarefas" (Tasks) feature will be entirely replaced by "Meus Alertas" (Alarms). Any existing tasks will be removed.

## Proposed Changes

### Data Layer

#### [NEW] [Alarm.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/data/Alarm.kt)
Create the `Alarm` data class.
```kotlin
data class Alarm(
    val id: String = UUID.randomUUID().toString(),
    val label: String,
    val time: String, // format "HH:mm"
    val isEnabled: Boolean = true,
    val type: AlarmType = AlarmType.Routine
)

enum class AlarmType {
    Medication, WakeUp, Routine
}
```

#### [DELETE] [Task.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/data/Task.kt)
#### [DELETE] [Task.java](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/model/java/Task.java)

---

### UI Layer

#### [NEW] [AlarmViewModel.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/alarms/AlarmViewModel.kt)
Handle alarm state, including adding, deleting, and toggling alarms.

#### [NEW] [AlarmComponents.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/alarms/AlarmComponents.kt)
Reusable components for the Alarm screen (AlarmItem, AddAlarmDialog).

#### [MODIFY] [Screens.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/Screens.kt)
- Add `CelestialClock` component.
- Update `HomeScreen` to include `CelestialClock`.
- Replace `TasksScreen` with `AlertsScreen`.
- Remove legacy task-related composables.

#### [MODIFY] [LanguageViewModel.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/LanguageViewModel.kt)
Update strings for PT, EN, and ES to include alarm-related terms and update navigation labels.

#### [MODIFY] [MyDayNavHost.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/navigation/MyDayNavHost.kt)
Update navigation routes and labels: "Tasks" -> "Alerts".

#### [DELETE] [ui/tasks/](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/tasks/)
Remove old task UI components and ViewModel.

---

### Verification Plan

#### Automated Tests
- Run existing unit tests to ensure no regressions in other areas.
- Create basic unit tests for `AlarmViewModel`.

#### Manual Verification
- Verify `CelestialClock` transitions correctly based on system time (Sun during day, Moon at night).
- Test adding, toggling, and deleting alarms in the new Alerts screen.
- Verify localization for all new strings in PT, EN, and ES.
- Check "Bianca Style" consistency (Dark Purple, floating cards, stickers).
