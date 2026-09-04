package com.example.myday.receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.myday.MainActivity
import com.example.myday.R
import com.example.myday.data.AppDatabase
import com.example.myday.data.AlarmRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            rescheduleAlarms(context)
            return
        }

        val alarmId = intent.getIntExtra("ALARM_ID", -1)
        val alarmLabel = intent.getStringExtra("ALARM_LABEL") ?: "Celestial Alarm"

        showNotification(context, alarmId, alarmLabel)
    }

    private fun showNotification(context: Context, alarmId: Int, label: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "celestial_alarm_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Celestial Alarms",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Alarm notifications for My Day"
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }

        val dismissIntent = Intent(context, DismissReceiver::class.java).apply {
            putExtra("NOTIFICATION_ID", alarmId)
        }
        val dismissPendingIntent = PendingIntent.getBroadcast(
            context,
            alarmId,
            dismissIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val mainIntent = Intent(context, MainActivity::class.java)
        val mainPendingIntent = PendingIntent.getActivity(
            context,
            0,
            mainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle("✨ My Day Alarm")
            .setContentText(label)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true)
            .setSound(soundUri)
            .setVibrate(longArrayOf(0, 500, 200, 500))
            .setContentIntent(mainPendingIntent)
            .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Dismiss", dismissPendingIntent)
            .build()

        notificationManager.notify(alarmId, notification)
    }

    private fun rescheduleAlarms(context: Context) {
        val database = AppDatabase.getDatabase(context)
        val repository = AlarmRepository(context, database.alarmDao())
        CoroutineScope(Dispatchers.IO).launch {
            repository.allAlarms.collect { alarms ->
                alarms.forEach { alarm ->
                    if (alarm.isEnabled) {
                        // Reschedule logic is inside repository.update but we can call a dedicated method if needed
                        // For simplicity, I'll just call repository.update(alarm)
                        repository.update(alarm)
                    }
                }
            }
        }
    }
}
