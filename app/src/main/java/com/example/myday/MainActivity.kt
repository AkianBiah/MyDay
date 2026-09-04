package com.example.myday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myday.data.AppDatabase
import com.example.myday.data.RoutineRepository
import com.example.myday.data.AlarmRepository
import com.example.myday.navigation.MyDayNavHost
import com.example.myday.ui.theme.MyDayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val database = AppDatabase.getDatabase(this)
        val routineRepository = RoutineRepository(database.routineDao())
        val alarmRepository = AlarmRepository(this, database.alarmDao())
        
        enableEdgeToEdge()
        setContent {
            MyDayTheme {
                MyDayNavHost(
                    routineRepository = routineRepository,
                    alarmRepository = alarmRepository
                )
            }
        }
    }
}
