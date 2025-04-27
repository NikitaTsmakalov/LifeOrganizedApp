package com.example.lifeorganizedapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifeorganizedapp.R
import com.example.lifeorganizedapp.data.Project
import com.example.lifeorganizedapp.data.Task
import com.example.lifeorganizedapp.ui.theme.LifeOrganizedAppTheme
import com.example.lifeorganizedapp.ui.theme.TextColor
import com.example.lifeorganizedapp.ui.viewmodel.MainViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
    val userName = viewModel.userName.collectAsState().value
    val tasks = viewModel.tasks.collectAsState().value
    val projects = viewModel.projects.collectAsState().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.list_ic),
                    contentDescription = "Settings Icon",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { navController.navigate("general_settings") }
                )

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF1A2A44))
                        .clickable { navController.navigate("settings") }
                ) {
                    // TODO: Добавить аватарку, например, Image(painterResource(R.drawable.user_avatar))
                }
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
            Text(
                text = "Привет, $userName!",
                style = MaterialTheme.typography.headlineMedium,
                color = TextColor,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Мои задачи",
                style = MaterialTheme.typography.titleLarge,
                color = TextColor
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tasks) { task ->
                    TaskCard(task)
                }
            }

            Text(
                text = "Ваш прогресс проектов",
                style = MaterialTheme.typography.titleLarge,
                color = TextColor
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.height(500.dp)
            ) {
                items(projects) { project ->
                    ProjectCard(project)
                }
            }
        }
    }
}

@Composable
fun TaskCard(task: Task) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(task.backgroundColor)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = task.iconResId),
                contentDescription = task.title,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = task.title,
            style = MaterialTheme.typography.titleMedium,
            color = TextColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${task.subTaskCount} задач",
            style = MaterialTheme.typography.bodySmall,
            color = TextColor.copy(alpha = 0.7f),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ProjectCard(project: Project) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF5F5F5))
            .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = project.iconResId),
                    contentDescription = project.title,
                    modifier = Modifier.size(32.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = project.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = project.createdDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextColor.copy(alpha = 0.7f)
                )
            }
        }

        Box(
            modifier = Modifier.size(48.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = project.progressPercent / 100f,
                modifier = Modifier.size(48.dp),
                color = Color(0xFF26A69A),
                trackColor = Color(0xFFE0E0E0),
                strokeWidth = 5.dp
            )
            Text(
                text = "${project.progressPercent}%",
                style = MaterialTheme.typography.bodySmall,
                color = TextColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

open class PreviewMainViewModel : MainViewModel() {
    override val userName: StateFlow<String> = MutableStateFlow("Никита")
    override val tasks: StateFlow<List<Task>> = MutableStateFlow(
        listOf(
            Task(id = 1, title = "Работа", iconResId = android.R.drawable.ic_menu_manage, subTaskCount = 5, backgroundColor = Color(0xFFE6F0FA)),
            Task(id = 2, title = "Личное", iconResId = android.R.drawable.ic_menu_agenda, subTaskCount = 3, backgroundColor = Color(0xFFFFF3E0)),
            Task(id = 3, title = "Учёба", iconResId = android.R.drawable.ic_menu_edit, subTaskCount = 7, backgroundColor = Color(0xFFD4EDDA))
        )
    )
    override val projects: StateFlow<List<Project>> = MutableStateFlow(
        listOf(
            Project(id = 1, title = "Проект A", iconResId = android.R.drawable.ic_menu_upload, createdDate = "12.05.2025", progressPercent = 75),
            Project(id = 2, title = "Проект B", iconResId = android.R.drawable.ic_menu_share, createdDate = "10.04.2025", progressPercent = 30),
            Project(id = 3, title = "Проект C", iconResId = android.R.drawable.ic_menu_save, createdDate = "01.03.2025", progressPercent = 90)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    LifeOrganizedAppTheme {
        MainScreen(
            navController = rememberNavController(),
            viewModel = PreviewMainViewModel()
        )
    }
}