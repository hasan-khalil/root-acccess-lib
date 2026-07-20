package com.example.firepassword.ui.theme.TestTheme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MultiThemeApp(
    themeType: AppThemeType,
    content: @Composable () -> Unit
) {
    // Select the correct scheme based on the chosen enum state
    val colorScheme = when (themeType) {
        AppThemeType.LIGHT -> LightColors
        AppThemeType.DARK -> DarkColors
        AppThemeType.BLUE_OCEAN -> BlueOceanColors
        AppThemeType.PINK_FLAMINGO -> PinkFlamingoColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography, // Use default or custom typography
        shapes = MaterialTheme.shapes,         // Use default or custom shapes
        content = content
    )
}