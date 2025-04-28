package com.example.lifeorganizedapp.ui.viewmodel

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.lifeorganizedapp.R
import com.example.lifeorganizedapp.data.Project
import com.example.lifeorganizedapp.data.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

open class MainViewModel(private val context: Context) : ViewModel() {
    open val userName: StateFlow<String> = MutableStateFlow("Никита")

    private val _tasks = MutableStateFlow(loadTasksFromPrefs())
    open val tasks: StateFlow<List<Task>> = _tasks

    open val projects: StateFlow<List<Project>> = MutableStateFlow(
        listOf(
            Project(id = 1, title = "Проект A", iconResId = R.drawable.star_ic, createdDate = "12.05.2025", progressPercent = 75),
            Project(id = 2, title = "Проект B", iconResId = R.drawable.language_ic, createdDate = "10.04.2025", progressPercent = 30),
            Project(id = 3, title = "Проект C", iconResId = R.drawable.money_ic, createdDate = "01.03.2025", progressPercent = 90)
        )
    )

    fun addTask(task: Task) {
        _tasks.update { currentTasks ->
            println("Adding task: $task")
            val newList = currentTasks + task
            println("New tasks list: $newList")
            saveTasksToPrefs(newList)
            newList
        }
    }

    private fun saveTasksToPrefs(tasks: List<Task>) {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(tasks)
        prefs.edit().putString("tasks", json).apply()
        println("Saved tasks to SharedPreferences: $json")
    }

    private fun loadTasksFromPrefs(): List<Task> {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = prefs.getString("tasks", null)
        return if (json != null) {
            val type = object : TypeToken<List<Task>>() {}.type
            val tasks = gson.fromJson<List<Task>>(json, type) ?: listOf()
            println("Loaded tasks from SharedPreferences: $tasks")
            tasks
        } else {
            // Начальные задачи, если SharedPreferences пуст
            listOf(
                Task(id = 1, title = "Работа", iconResId = R.drawable.learn_ic, subTaskCount = 5, backgroundColor = Color(0xFFE6F0FA)),
                Task(id = 2, title = "Личное", iconResId = R.drawable.read_ic, subTaskCount = 3, backgroundColor = Color(0xFFFFF3E0)),
                Task(id = 3, title = "Учёба", iconResId = R.drawable.logic_ic, subTaskCount = 7, backgroundColor = Color(0xFFD4EDDA))
            ).also {
                println("Loaded default tasks: $it")
            }
        }
    }
}