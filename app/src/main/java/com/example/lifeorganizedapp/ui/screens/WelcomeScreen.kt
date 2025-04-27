package com.example.lifeorganizedapp.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lifeorganizedapp.R
import com.example.lifeorganizedapp.ui.theme.LifeOrganizedAppTheme
import com.example.lifeorganizedapp.ui.theme.TextColorWelcomeScreen
import com.example.lifeorganizedapp.ui.theme.WelcomeScreenBgEnd
import com.example.lifeorganizedapp.ui.theme.WelcomeScreenBgStart
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(onNavigateToMain: () -> Unit) {
    // Состояние для управления анимацией
    var animationState by remember { mutableStateOf(AnimationState.FadeIn) }

    // Анимация появления
    val alpha by androidx.compose.animation.core.animateFloatAsState(
        targetValue = if (animationState == AnimationState.FadeIn || animationState == AnimationState.Rotate) 1f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        label = "fadeIn"
    )

    // Анимация поворота
    val transition = updateTransition(targetState = animationState, label = "rotation")
    val rotation by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 500, easing = LinearEasing)
        },
        label = "rotate"
    ) { state ->
        when (state) {
            AnimationState.FadeIn -> 0f
            AnimationState.Rotate -> 5f // Поворот на 5 градусов
            AnimationState.RotateBack -> -5f // Поворот на -5 градусов
        }
    }

    // Запуск анимации
    LaunchedEffect(Unit) {
        // Ждем завершения появления (1 секунда)
        delay(1000)
        animationState = AnimationState.Rotate
        delay(700) // Поворот вправо
        animationState = AnimationState.RotateBack
        delay(700) // Поворот влево
        animationState = AnimationState.Rotate
        delay(700) // Снова вправо
        // Переход на главный экран через 2 секунды после начала
        delay(1000)
        onNavigateToMain()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(WelcomeScreenBgStart, WelcomeScreenBgEnd)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.loding_img),
            contentDescription = "Welcome Image",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 24.dp)
                .alpha(alpha) // Применяем анимацию появления
                .rotate(rotation), // Применяем анимацию поворота
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Измени свою жизнь с LifeOrganized",
            style = MaterialTheme.typography.headlineMedium,
            color = TextColorWelcomeScreen,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

// Состояния для анимации
private enum class AnimationState {
    FadeIn, Rotate, RotateBack
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    LifeOrganizedAppTheme {
        WelcomeScreen(onNavigateToMain = {})
    }
}