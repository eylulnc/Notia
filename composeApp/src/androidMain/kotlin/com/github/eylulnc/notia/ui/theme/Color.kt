package com.github.eylulnc.notia.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF4A4A4A)
val Cream = Color(0xFFFDFCF8)
val Charcoal = Color(0xFF333333)
val CharcoalSoft = Color(0xFF555555)
val BackgroundLight = Color(0xFFF9F7F2)
val BackgroundDark = Color(0xFF1A1A1A)
val MutedRed = Color(0xFFC86B6B)

val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = Color.White,

    secondary = Charcoal,
    onSecondary = Color.White,

    tertiary = MutedRed,
    onTertiary = Color.White,

    background = BackgroundLight,
    onBackground = Charcoal,

    surface = Cream,
    onSurface = Charcoal,

    surfaceVariant = Color(0xFFEBE9E2),
    onSurfaceVariant = CharcoalSoft,

    outline = CharcoalSoft.copy(alpha = 0.4f)
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF9FADE6),
    onPrimary = Color.Black,

    secondary = Color(0xFFB6C1E6),
    onSecondary = Color.Black,

    tertiary = MutedRed,
    onTertiary = Color.White,

    background = BackgroundDark,
    onBackground = Cream,

    surface = Color(0xFF262626),
    onSurface = Cream,

    surfaceVariant = Color(0xFF2A2E38),
    onSurfaceVariant = Color(0xFFB8BCC6),

    outline = Color(0xFF3A3F4B)
)
