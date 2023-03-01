package com.example.calendarapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_NO_CREATE
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.SystemClock
import android.util.Log

import java.util.*

class MyReceiver2 : BroadcastReceiver() {
    private var tag = "calendar_app_dbg2";

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


        val intent = Intent(context, MyReceiver2::class.java)
        var pendingIntent =
            PendingIntent.getService(context, 100, intent, PendingIntent.FLAG_NO_CREATE)
        if (pendingIntent != null) {
            Log.i(tag, "try cancel old alarm")
            alarmMgr.cancel(pendingIntent)
        }

        pendingIntent =
            PendingIntent.getService(context, 100, intent, 0)

        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() +  AlarmManager.INTERVAL_HOUR * 4,
            pendingIntent)
    }
}
