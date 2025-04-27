package com.example.lifeorganizedapp.ui.screens

import android.content.Context

class FirstLaunchManager(context: Context) {
    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    private val keyFirstLaunch = "is_first_launch"

    fun isFirstLaunch(): Boolean {
        return prefs.getBoolean(keyFirstLaunch, true)
    }

    fun setFirstLaunchCompleted() {
        prefs.edit().putBoolean(keyFirstLaunch, false).apply()
    }
}