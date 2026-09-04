# Implementation Plan - Daily Routines Feature

This plan outlines the implementation of a "Daily Routines" feature for the My Day app. Daily Routines are recurring tasks that reset every day, helping users maintain consistent habits with a "magical" and encouraging UI.

## User Review Required

> [!IMPORTANT]
> - **Daily Reset Logic**: The current plan is to reset the completion status of all routines when the app is first opened on a new calendar day.
> - **Icon Selection**: I will provide a curated set of "cute" icons for routines. Does the user want a specific set or should I choose? (Default: Water, Exercise, Reading, Sleep, Sun, Meditation).
> - **Celebration**: The celebration will be a combination of a Snackbar and a custom "Sparkle" overlay on the completed item.

## Proposed Changes

### [Data Layer]

#### [NEW] [Routine.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/data/Routine.kt)
Define the `Routine` entity for Room.
- Fields: `id`, `name`, `iconName`, `isCompleted`, `lastCompletedDate` (Long).

#### [NEW] [RoutineDao.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/data/RoutineDao.kt)
Room DAO for `Routine` operations (Insert, Update, Delete, Query all).

#### [NEW] [AppDatabase.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/data/AppDatabase.kt)
Room Database class to hold the `Routine` table.

#### [NEW] [RoutineRepository.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/data/RoutineRepository.kt)
Handles the business logic for routines, including the daily reset check.

---

### [ViewModel Layer]

#### [NEW] [RoutineViewModel.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/routines/RoutineViewModel.kt)
Manages the state of routines and triggers celebrations.
- Uses `RoutineRepository` to fetch and update data.
- Exposes a `List<Routine>` state.
- Handles "Well Done!" events.

---

### [UI Layer]

#### [NEW] [RoutineComponents.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/routines/RoutineComponents.kt)
Reusable UI components for routines.
- `RoutineItem`: A card with a cute icon and a completion toggle.
- `CelebrationEffect`: Sparkle animation overlay.
- `IconPicker`: A grid of icons to choose from in the "Add Routine" dialog.

#### [MODIFY] [Screens.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/Screens.kt)
Add `RoutinesScreen` composable.
- Displays the list of routines.
- Includes a Floating Action Button (FAB) or Header button to add new routines.
- Progress tracking (e.g., "3/5 completed").

---

### [Integration & Navigation]

#### [MODIFY] [MyDayNavHost.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/navigation/MyDayNavHost.kt)
- Add `MyDayRoute.Routines` to the route list.
- Add a new item to the `NavigationSuiteScaffold` for "Rotinas".
- Register `RoutinesScreen` in the `NavDisplay` entry provider.

#### [MODIFY] [LanguageViewModel.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/LanguageViewModel.kt)
Add translations for:
- "routines_title": Routines / Rotinas
- "add_routine": Add Routine / Adicionar Rotina
- "routine_name": Name / Nome
- "select_icon": Select Icon / Selecionar Ícone
- "well_done": Well Done! / Muito Bem! ✨
- "progress_label": completed / concluídas

---

### [MainActivity]

#### [MODIFY] [MainActivity.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/MainActivity.kt)
Initialize the `AppDatabase` and provide the repository (simple manual injection for now, or use a Singleton).

## Verification Plan

### Automated Tests
- **Unit Tests**: `RoutineViewModelTest` to verify that completing a routine triggers the celebration event and updates the repository.
- **Room Tests**: Test the DAO to ensure data is persisted correctly.

### Manual Verification
- Open the "Rotinas" tab.
- Add a new routine with a custom icon.
- Toggle completion and verify the "Muy Bem! / Well Done!" visual feedback.
- Change the system date to tomorrow and verify the routines reset to incomplete.
- Verify that icons are "cute" and consistent with the app's aesthetic.
