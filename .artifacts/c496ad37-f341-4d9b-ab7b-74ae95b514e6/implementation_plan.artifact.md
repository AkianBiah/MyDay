# Implementation Plan - Daily Routines Feature

Add a "Daily Routines" feature to the 'My Day' app, including data models, recurring logic, celebratory UI feedback, and navigation updates.

## User Review Required

> [!IMPORTANT]
> The routines will be stored in-memory in the `RoutineViewModel` for now, consistent with the existing `TaskViewModel` implementation. They will reset daily based on the system clock.

## Proposed Changes

### Data Layer

#### [NEW] [Routine.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/data/Routine.kt)
- Define `Routine` data class: `id`, `title`, `icon` (Emoji string), `isCompletedToday`, `lastCompletedDate`.

### Logic & State Management

#### [MODIFY] [LanguageViewModel.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/LanguageViewModel.kt)
- Add routine-related strings: "routines_title", "add_routine_hint", "routines_empty", "very_well", "routines_done_today", "add_routine_btn".

#### [NEW] [RoutineViewModel.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/routines/RoutineViewModel.kt)
- Manage a list of `Routine` items.
- Logic to reset `isCompletedToday` if `lastCompletedDate` is not the current day.
- Methods to add, toggle, and delete routines.
- Track total stars (completed routines + tasks).

### UI Layer

#### [NEW] [RoutineComponents.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/routines/RoutineComponents.kt)
- `RoutineItem`: Cute card for each routine with emoji icon.
- `CelebratoryFeedback`: An animated overlay or message when a routine is completed.
- `AddRoutineForm`: Form to add new recurring routines.

#### [NEW] [RoutinesScreen.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/routines/RoutinesScreen.kt)
- Screen to list routines, show stats, and add new ones.

#### [MODIFY] [MyDayNavHost.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/navigation/MyDayNavHost.kt)
- Add `Routines` to `MyDayRoute`.
- Update `NavigationSuiteScaffold` to include the Routines tab.
- Add Routines screen to `NavDisplay`.

#### [MODIFY] [Screens.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/Screens.kt)
- Update `StatCounter` or add a new global Star Counter that combines tasks and routines.

## Verification Plan

### Automated Tests
- Unit test for `RoutineViewModel` logic (resetting daily).

### Manual Verification
- Verify the Routines screen appears in the navigation.
- Add a new routine and complete it.
- Verify the celebratory feedback message ("Very Well! / Muito Bem! 🌟").
- Verify the star counter increases.
- Simulate a date change to verify routines reset.
