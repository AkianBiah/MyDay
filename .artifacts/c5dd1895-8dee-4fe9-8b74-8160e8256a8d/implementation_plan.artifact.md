# Fix Language Switching and Update Home Screen UI

This plan addresses the language switching bug by ensuring a shared `LanguageViewModel` instance is used across all screens. It also updates the Home Screen to include a localized mini-calendar element.

## Proposed Changes

### Navigation

#### [MODIFY] [MyDayNavHost.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/navigation/MyDayNavHost.kt)
- Pass the `languageViewModel` instance to all screens (`HomeScreen`, `TasksScreen`, `SettingsScreen`).

### UI Components

#### [MODIFY] [KawaiiComponents.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/KawaiiComponents.kt)
- Update `KawaiiClock` to include a "mini calendar" element.
- The mini calendar will display the day of the week and the date.
- The aesthetic will follow the Kawaii "bubble/cloud" style.
- Ensure all components receive `LanguageViewModel` if they need localized strings.

#### [MODIFY] [Screens.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/Screens.kt)
- Update `HomeScreen` to pass the `formattedDate` and `formattedTime` correctly to the updated components.
- Ensure all screens use the passed `LanguageViewModel`.

### ViewModels

#### [MODIFY] [LanguageViewModel.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/LanguageViewModel.kt)
- Add a helper function to get the `Locale` based on the current `AppLanguage`.
- This will be useful for formatting dates and times in `WeatherViewModel` or UI.

#### [MODIFY] [WeatherViewModel.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/WeatherViewModel.kt)
- Add a way to update the `Locale` used for date/time formatting when the language changes.
- Provide separate flows for "Day of Week" and "Date" if needed for the mini calendar.

## Verification Plan

### Automated Tests
- N/A (Manual verification is more suitable for UI/UX and immediate state updates).

### Manual Verification
1. Run the app and go to the Settings screen.
2. Change the language to Portuguese, English, and Spanish.
3. Verify that all UI strings (Home greeting, Task hints, etc.) update immediately.
4. Go to the Home screen and verify the presence of the mini calendar alongside the clock.
5. Verify that the day of the week and date in the mini calendar are localized correctly (e.g., "Monday" vs "Segunda-feira").
6. Check that the Kawaii aesthetic is preserved.
