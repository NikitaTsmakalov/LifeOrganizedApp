package com.example.lifeorganizedapp.data

import androidx.compose.ui.graphics.Color

data class Task(
    val id: Int,
    val title: String,
    val iconResId: Int,
    val subTaskCount: Int,
    val backgroundColor: Color
)
