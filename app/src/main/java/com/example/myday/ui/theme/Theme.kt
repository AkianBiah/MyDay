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

private val KawaiiDayColorScheme = lightColorScheme(
    primary = PinkPrimary,
    onPrimary = PinkOnPrimary,
    primaryContainer = PinkPrimaryContainer,
    onPrimaryContainer = PinkOnPrimaryContainer,
    secondary = PinkSecondary,
    onSecondary = PinkOnSecondary,
    secondaryContainer = PinkSecondaryContainer,
    onSecondaryContainer = PinkOnSecondaryContainer,
    tertiary = PinkTertiary,
    onTertiary = PinkOnTertiary,
    tertiaryContainer = PinkTertiaryContainer,
    onTertiaryContainer = PinkOnTertiaryContainer,
    background = PinkBackground,
    onBackground = PinkOnBackground,
    surface = PinkSurface,
    onSurface = PinkOnSurface,
    surfaceVariant = PinkSurfaceVariant,
    onSurfaceVariant = PinkOnSurfaceVariant,
)

private val DarkKawaiiColorScheme = darkColorScheme(
    primary = DarkKawaiiPrimary,
    onPrimary = DarkKawaiiOnPrimary,
    primaryContainer = DarkKawaiiPrimaryContainer,
    onPrimaryContainer = DarkKawaiiOnPrimaryContainer,
    secondary = DarkKawaiiSecondary,
    onSecondary = DarkKawaiiOnSecondary,
    secondaryContainer = DarkKawaiiSecondaryContainer,
    onSecondaryContainer = DarkKawaiiOnSecondaryContainer,
    tertiary = DarkKawaiiTertiary,
    onTertiary = DarkKawaiiOnTertiary,
    tertiaryContainer = DarkKawaiiTertiaryContainer,
    onTertiaryContainer = DarkKawaiiOnTertiaryContainer,
    background = DarkKawaiiBackground,
    onBackground = DarkKawaiiOnBackground,
    surface = DarkKawaiiSurface,
    onSurface = DarkKawaiiOnSurface,
    surfaceVariant = DarkKawaiiSurfaceVariant,
    onSurfaceVariant = DarkKawaiiOnSurfaceVariant,
)

@Composable
fun MyDayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Set to false to prioritize Kawaii theme
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
        // Force Kawaii Night if it's night time, otherwise Kawaii Day
        !isDayTime || darkTheme -> DarkKawaiiColorScheme
        else -> KawaiiDayColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = KawaiiShapes,
        content = content
    )
}
