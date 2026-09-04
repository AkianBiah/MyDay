package com.example.myday.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

private val RoseQuartzColorScheme = lightColorScheme(
    primary = RoseQuartzPrimary,
    onPrimary = RoseQuartzOnPrimary,
    primaryContainer = RoseQuartzPrimaryContainer,
    onPrimaryContainer = RoseQuartzOnPrimaryContainer,
    secondary = RoseQuartzSecondary,
    onSecondary = RoseQuartzOnSecondary,
    secondaryContainer = RoseQuartzSecondaryContainer,
    onSecondaryContainer = RoseQuartzOnSecondaryContainer,
    tertiary = RoseQuartzTertiary,
    onTertiary = RoseQuartzOnTertiary,
    tertiaryContainer = RoseQuartzTertiaryContainer,
    onTertiaryContainer = RoseQuartzOnTertiaryContainer,
    background = RoseQuartzBackground,
    onBackground = RoseQuartzOnBackground,
    surface = RoseQuartzSurface,
    onSurface = RoseQuartzOnSurface,
    surfaceVariant = RoseQuartzSurfaceVariant,
    onSurfaceVariant = RoseQuartzOnSurfaceVariant,
    outline = RoseQuartzOutline
)

private val AmethystColorScheme = darkColorScheme(
    primary = AmethystPrimary,
    onPrimary = AmethystOnPrimary,
    primaryContainer = AmethystPrimaryContainer,
    onPrimaryContainer = AmethystOnPrimaryContainer,
    secondary = AmethystSecondary,
    onSecondary = AmethystOnSecondary,
    secondaryContainer = AmethystSecondaryContainer,
    onSecondaryContainer = AmethystOnSecondaryContainer,
    tertiary = AmethystTertiary,
    onTertiary = AmethystOnTertiary,
    tertiaryContainer = AmethystTertiaryContainer,
    onTertiaryContainer = AmethystOnTertiaryContainer,
    background = AmethystBackground,
    onBackground = AmethystOnBackground,
    surface = AmethystSurface,
    onSurface = AmethystOnSurface,
    surfaceVariant = AmethystSurfaceVariant,
    onSurfaceVariant = AmethystOnSurfaceVariant,
    outline = AmethystOutline
)

@Composable
fun MyDayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Determine if it's day or night based on the 6 AM - 6 PM rule
    val isDayTime = remember {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        hour in 6..17
    }

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        // If not dynamic, use our custom sophisticated palettes
        darkTheme || !isDayTime -> AmethystColorScheme
        else -> RoseQuartzColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = AppShapes,
        content = content
    )
}
