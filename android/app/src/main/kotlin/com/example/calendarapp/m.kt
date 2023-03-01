package com.example.calendarapp

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import java.util.*

object M {
    private var tag = "calendar_app_dbgm";

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


    fun setIcon(context: Context) {
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