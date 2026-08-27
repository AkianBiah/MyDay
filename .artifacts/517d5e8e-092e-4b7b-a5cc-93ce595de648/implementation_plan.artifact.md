# Implementation Plan - Task Tracker UI

This plan covers the implementation of Task 2: Task Tracker UI for the "My Day" app, featuring a Kawaii aesthetic and full task management functionality.

## Proposed Changes

### Data Layer

#### [NEW] [Task.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/data/Task.kt)
Create a data class for tasks.
- `id: UUID`
- `description: String`
- `isCompleted: Boolean`

### ViewModel Layer

#### [NEW] [TaskViewModel.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/tasks/TaskViewModel.kt)
Create a ViewModel to manage task state.
- `taskList: StateFlow<List<Task>>`
- `addTask(description: String)`
- `toggleTaskCompletion(taskId: UUID)`
- `deleteTask(taskId: UUID)`

### UI Layer

#### [MODIFY] [Screens.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/Screens.kt)
Update `TasksScreen` to include:
- A `TaskViewModel` instance.
- A list of tasks using `LazyColumn`.
- `TaskItem` component for each task.
- An "Add Task" input section with a cute button.

#### [NEW] [TaskComponents.kt](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/ui/tasks/TaskComponents.kt)
Create reusable components for the Task UI.
- `KawaiiTaskItem`: Displays a task with a checkbox (heart/star) and delete button.
- `KawaiiAddTaskField`: A styled text field for adding new tasks.

## Verification Plan

### Automated Tests
- Unit test for `TaskViewModel` to ensure adding, deleting, and toggling tasks work correctly.

### Manual Verification
- Launch the app and navigate to the Tasks screen.
- Verify that adding a task works.
- Verify that clicking the completion icon toggles the state.
- Verify that deleting a task removes it from the list.
- Verify that the Kawaii aesthetic (rounded corners, pink/purple colors) is consistently applied.
