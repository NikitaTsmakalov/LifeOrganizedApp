package com.example.lifeorganizedapp.ui.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.lifeorganizedapp.data.Project
import com.example.lifeorganizedapp.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class MainViewModel : ViewModel() {
    open val userName: StateFlow<String> = MutableStateFlow("Никита")

    open val tasks: StateFlow<List<Task>> = MutableStateFlow(
        listOf(
            Task(id = 1, title = "Работа", iconResId = android.R.drawable.ic_menu_manage, subTaskCount = 5, backgroundColor = Color(0xFFE6F0FA)),
            Task(id = 2, title = "Личное", iconResId = android.R.drawable.ic_menu_agenda, subTaskCount = 3, backgroundColor = Color(0xFFFFF3E0)),
            Task(id = 3, title = "Учёба", iconResId = android.R.drawable.ic_menu_edit, subTaskCount = 7, backgroundColor = Color(0xFFD4EDDA))
        )
    )

    open val projects: StateFlow<List<Project>> = MutableStateFlow(
        listOf(
            Project(id = 1, title = "Проект A", iconResId = android.R.drawable.ic_menu_upload, createdDate = "12.05.2025", progressPercent = 75),
            Project(id = 2, title = "Проект B", iconResId = android.R.drawable.ic_menu_share, createdDate = "10.04.2025", progressPercent = 30),
            Project(id = 3, title = "Проект C", iconResId = android.R.drawable.ic_menu_save, createdDate = "01.03.2025", progressPercent = 90)
        )
    )
}