package com.example.lifeorganizedapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lifeorganizedapp.R
import com.example.lifeorganizedapp.data.Task
import com.example.lifeorganizedapp.ui.theme.TextColor
import com.example.lifeorganizedapp.ui.viewmodel.MainViewModel

@Composable
fun AddTaskScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: MainViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(context) as T
            }
        }
    )
    // Состояния
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val subTasks = remember { mutableStateListOf<String>() }
    var selectedIcon by remember { mutableStateOf(R.drawable.learn_ic) }
    var selectedColor by remember { mutableStateOf(Color(0xFFE6F0FA)) }

    // Список иконок
    val icons = listOf(
        R.drawable.learn_ic,
        R.drawable.read_ic,
        R.drawable.logic_ic,
        R.drawable.imp_ic,
        R.drawable.star_ic,
        R.drawable.language_ic,
        R.drawable.money_ic,
        R.drawable.time_ic
    )

    // Палитра цветов
    val colors = listOf(
        Color(0xFFE6F0FA), // Светло-голубой
        Color(0xFFFFF3E0), // Светло-оранжевый
        Color(0xFFD4EDDA), // Светло-зеленый
        Color(0xFFF8E1E9), // Светло-розовый
        Color(0xFFEDE7F6), // Светло-фиолетовый
        Color(0xFFE0F7FA)  // Светло-бирюзовый
    )

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { navController.navigate("main") }
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Заголовок экрана
            Text(
                text = "Новая задача",
                style = MaterialTheme.typography.headlineSmall,
                color = TextColor,
                fontWeight = FontWeight.Bold
            )

            // Поле заголовка
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Название задачи") },
                modifier = Modifier.fillMaxWidth()
            )

            // Поле описания
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Описание") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 5
            )

            // Подзадачи
            Text(
                text = "Подзадачи",
                style = MaterialTheme.typography.titleMedium,
                color = TextColor
            )
            subTasks.forEachIndexed { index, subTask ->
                OutlinedTextField(
                    value = subTask,
                    onValueChange = { subTasks[index] = it },
                    label = { Text("Подзадача ${index + 1}") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Button(
                onClick = { subTasks.add("") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Добавить подзадачу")
            }

            // Выбор иконки
            Text(
                text = "Иконка",
                style = MaterialTheme.typography.titleMedium,
                color = TextColor
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(icons) { icon ->
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (selectedIcon == icon) Color(0xFF26A69A) else Color.White)
                            .clickable { selectedIcon = icon },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = icon),
                            contentDescription = "Icon",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }

            // Выбор цвета
            Text(
                text = "Цвет",
                style = MaterialTheme.typography.titleMedium,
                color = TextColor
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(colors) { color ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = if (selectedColor == color) 3.dp else 1.dp,
                                color = if (selectedColor == color) Color(0xFF26A69A) else Color.Gray,
                                shape = CircleShape
                            )
                            .clickable { selectedColor = color }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Кнопка создания задачи
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        val newTask = Task(
                            id = (viewModel.tasks.value.maxOfOrNull { task: Task -> task.id } ?: 0) + 1,
                            title = title,
                            iconResId = selectedIcon,
                            subTaskCount = subTasks.filter { it.isNotBlank() }.size,
                            backgroundColor = selectedColor
                        )
                        println("Creating task: $newTask") // Отладочный лог
                        viewModel.addTask(newTask)
                        navController.navigate("main") {
                            popUpTo("main") { inclusive = false }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                enabled = title.isNotBlank()
            ) {
                Text("Создать задачу")
            }
        }
    }
}