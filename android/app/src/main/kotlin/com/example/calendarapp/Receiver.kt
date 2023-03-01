package com.example.calendarapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log

import java.util.*

class MyReceiver : BroadcastReceiver() {
    private var tag = "calendar_app_dbg";
    private var activitiesArray = listOf(
        "MainActivity",
        "ic_launcher1",
        "ic_launcher2",
        "ic_launcher3",
        "ic_launcher4",
        "ic_launcher5",
        "ic_launcher6",
        "ic_launcher7",
        "ic_launcher8",
        "ic_launcher9",
        "ic_launcher10",
        "ic_launcher11",
        "ic_launcher12",
        "ic_launcher13",
        "ic_launcher14",
        "ic_launcher15",
        "ic_launcher16",
        "ic_launcher17",
        "ic_launcher18",
        "ic_launcher19",
        "ic_launcher20",
        "ic_launcher21",
        "ic_launcher22",
        "ic_launcher23",
        "ic_launcher24",
        "ic_launcher25",
        "ic_launcher26",
        "ic_launcher27",
        "ic_launcher28",
        "ic_launcher29",
        "ic_launcher30",
        "ic_launcher31")

    override fun onReceive(context: Context, intent: Intent) {
        intent.action?.let { Log.i(tag, it) }

        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            setAlarm(context)
        } else if (Intent.ACTION_LOCKED_BOOT_COMPLETED == intent.action) {
            setAlarm(context)
        } else if ("android.intent.action.TIME_SET" == intent.action || "android.intent.action.TIMEZONE_CHANGED" == intent.action) {
            setAlarm(context)
            setIcon(context)
        } else {
            setIcon(context)
        }
    }

    private fun setAlarm(context: Context) {
        var alarmMgr: AlarmManager? = null

        alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent: PendingIntent = Intent(context, MyReceiver::class.java).let { intent ->
            PendingIntent.getService(context, 0, intent, 0)
        }

        alarmMgr.setRepeating(
            AlarmManager.RTC,
            System.currentTimeMillis(),
            AlarmManager.INTERVAL_HALF_HOUR,
            alarmIntent
        )
    }

    private fun setIcon(context: Context) {
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val targetIcon = "ic_launcher$day"

        val packageManager: PackageManager = context.packageManager
        val packageName = context.packageName
        val className = StringBuilder()
        className.append(packageName)
        className.append(".")
        className.append(targetIcon)

        for (value in activitiesArray) {
            val action = if (value == targetIcon) {
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            } else {
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED
            }

            val oldAction = packageManager.getComponentEnabledSetting(ComponentName(packageName!!, "$packageName.$value"))
            if (oldAction == action) {
                continue
            }

            Log.i(tag, "$value:$oldAction => $action")
            packageManager.setComponentEnabledSetting(
                ComponentName(packageName, "$packageName.$value"),
                action, PackageManager.DONT_KILL_APP
            )
        }
    }
}
