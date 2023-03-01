package com.example.calendarapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log

class MyReceiver : BroadcastReceiver() {
    private var tag = "calendar_app_dbg";

    override fun onReceive(context: Context, intent: Intent) {
        intent.action?.let { Log.i(tag, it) }

        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            setAlarm(context)
        } else if (Intent.ACTION_LOCKED_BOOT_COMPLETED == intent.action) {
            setAlarm(context)
        } else if ("android.intent.action.TIME_SET" == intent.action || "android.intent.action.TIMEZONE_CHANGED" == intent.action) {
            //M.setIcon(context)
        } else if ("com.example.calendarapp.REFRESH_ICON" == intent.action) {
            M.setIcon(context)
        } else if ("com.example.calendarapp.REFRESH_ALARM" == intent.action) {
            setAlarm(context)
        } else {
            M.setIcon(context)
        }
    }

    private fun setAlarm(context: Context) {
        var alarmMgr: AlarmManager? = null

        alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var intent = Intent(context, MyReceiver::class.java)

        var pendingIntent =
            PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_NO_CREATE)
        if (pendingIntent != null) {
            Log.i(tag, "try cancel old alarm")
            alarmMgr.cancel(pendingIntent)
        }

        pendingIntent = Intent(context, MyReceiver::class.java).let { intentX ->
            PendingIntent.getService(context, 0, intentX, 0)
        }

        alarmMgr.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime(),
            AlarmManager.INTERVAL_HALF_HOUR,
            pendingIntent
        )
    }
}
