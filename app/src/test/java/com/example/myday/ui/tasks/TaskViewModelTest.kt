package com.example.myday.ui.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TaskViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: TaskViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TaskViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `addTask should add a task to the list`() = runTest {
        viewModel.addTask("Test Task")
        assertEquals(1, viewModel.tasks.value.size)
        assertEquals("Test Task", viewModel.tasks.value[0].description)
    }

    @Test
    fun `toggleTaskCompletion should toggle task state`() = runTest {
        viewModel.addTask("Test Task")
        val taskId = viewModel.tasks.value[0].id
        
        viewModel.toggleTaskCompletion(taskId)
        assertTrue(viewModel.tasks.value[0].isCompleted)
        
        viewModel.toggleTaskCompletion(taskId)
        assertFalse(viewModel.tasks.value[0].isCompleted)
    }

    @Test
    fun `deleteTask should remove task from list`() = runTest {
        viewModel.addTask("Test Task")
        val taskId = viewModel.tasks.value[0].id
        
        viewModel.deleteTask(taskId)
        assertTrue(viewModel.tasks.value.isEmpty())
    }
}
