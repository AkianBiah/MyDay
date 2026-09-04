package com.example.myday.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import java.util.Calendar
import kotlin.random.Random

@Composable
fun AppBackground(
    content: @Composable () -> Unit
) {
    KawaiiBackground(content = content)
}

@Composable
fun KawaiiBackground(
    content: @Composable () -> Unit
) {
    val isDayTime = remember {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        hour in 6..17
    }

    val gradientColors = if (isDayTime) {
        listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
            MaterialTheme.colorScheme.background
        )
    } else {
        listOf(
            Color(0xFF1A1A2E), // Deep Midnight
            MaterialTheme.colorScheme.background
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(gradientColors))
    ) {
        // Subtle Pattern Overlay
        KawaiiPatternOverlay()
        
        // Subtle Grain Effect
        GrainOverlay()
        
        content()
    }
}

@Composable
fun KawaiiPatternOverlay() {
    val starPainter = rememberVectorPainter(Icons.Rounded.Star)
    val heartPainter = rememberVectorPainter(Icons.Rounded.Favorite)

    Canvas(modifier = Modifier.fillMaxSize()) {
        val random = Random(123)
        val gridSize = 100.dp.toPx()
        val cols = (size.width / gridSize).toInt() + 1
        val rows = (size.height / gridSize).toInt() + 1

        for (i in 0 until cols) {
            for (j in 0 until rows) {
                val x = i * gridSize + random.nextFloat() * gridSize
                val y = j * gridSize + random.nextFloat() * gridSize
                val useStar = random.nextBoolean()
                val rotation = random.nextFloat() * 360f
                val scale = 0.5f + random.nextFloat() * 0.5f

                withTransform({
                    translate(x, y)
                    rotate(rotation)
                    scale(scale, scale)
                }) {
                    if (useStar) {
                        val iconSize = 12.dp.toPx()
                        with(starPainter) {
                            draw(
                                size = androidx.compose.ui.geometry.Size(iconSize, iconSize),
                                alpha = 0.05f
                            )
                        }
                    } else {
                        val iconSize = 10.dp.toPx()
                        with(heartPainter) {
                            draw(
                                size = androidx.compose.ui.geometry.Size(iconSize, iconSize),
                                alpha = 0.05f
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FloatingDecoration(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
) {
    val infiniteTransition = rememberInfiniteTransition(label = "floating")
    
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )
    
    val translationY by infiniteTransition.animateFloat(
        initialValue = -4f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "translation"
    )

    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = modifier
            .offset(y = translationY.dp)
            .scale(scale)
            .alpha(0.8f),
        tint = color
    )
}

@Composable
fun GrainOverlay() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val random = Random(42)
        repeat(1000) {
            val x = random.nextFloat() * size.width
            val y = random.nextFloat() * size.height
            drawCircle(
                color = Color.White.copy(alpha = 0.03f),
                radius = 1.dp.toPx(),
                center = Offset(x, y)
            )
        }
    }
}
