package com.llimapons.core.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalExtendedColors = staticCompositionLocalOf { LightExtendedColors }

val ColorScheme.extended: ExtendedColors
    @ReadOnlyComposable
    @Composable
    get() = LocalExtendedColors.current

@Immutable
data class ExtendedColors(
    // Button states
    val primaryHover: Color,
    val destructiveHover: Color,
    val destructiveSecondaryOutline: Color,
    val disabledOutline: Color,
    val disabledFill: Color,
    val successOutline: Color,
    val success: Color,
    val onSuccess: Color,
    val secondaryFill: Color,

    // Text variants
    val textPrimary: Color,
    val textTertiary: Color,
    val textSecondary: Color,
    val textPlaceholder: Color,
    val textDisabled: Color,

    // Surface variants
    val surfaceLower: Color,
    val surfaceHigher: Color,
    val surfaceOutline: Color,
    val overlay: Color,

    // Accent colors
    val accentBlue: Color,
    val accentPurple: Color,
    val accentViolet: Color,
    val accentPink: Color,
    val accentOrange: Color,
    val accentYellow: Color,
    val accentGreen: Color,
    val accentTeal: Color,
    val accentLightBlue: Color,
    val accentGrey: Color,

    // Cake colors for chat bubbles
    val cakeViolet: Color,
    val cakeGreen: Color,
    val cakeBlue: Color,
    val cakePink: Color,
    val cakeOrange: Color,
    val cakeYellow: Color,
    val cakeTeal: Color,
    val cakePurple: Color,
    val cakeRed: Color,
    val cakeMint: Color,
)

val LightExtendedColors = ExtendedColors(
    primaryHover = EchoBrand600,
    destructiveHover = EchoRed600,
    destructiveSecondaryOutline = EchoRed200,
    disabledOutline = EchoBase200,
    disabledFill = EchoBase150,
    successOutline = EchoBrand100,
    success = EchoBrand600,
    onSuccess = EchoBase0,
    secondaryFill = EchoBase100,

    textPrimary = EchoBase1000,
    textTertiary = EchoBase800,
    textSecondary = EchoBase900,
    textPlaceholder = EchoBase700,
    textDisabled = EchoBase400,

    surfaceLower = EchoBase100,
    surfaceHigher = EchoBase100,
    surfaceOutline = EchoBase1000Alpha14,
    overlay = EchoBase1000Alpha80,

    accentBlue = EchoBlue,
    accentPurple = EchoPurple,
    accentViolet = EchoViolet,
    accentPink = EchoPink,
    accentOrange = EchoOrange,
    accentYellow = EchoYellow,
    accentGreen = EchoGreen,
    accentTeal = EchoTeal,
    accentLightBlue = EchoLightBlue,
    accentGrey = EchoGrey,

    cakeViolet = EchoCakeLightViolet,
    cakeGreen = EchoCakeLightGreen,
    cakeBlue = EchoCakeLightBlue,
    cakePink = EchoCakeLightPink,
    cakeOrange = EchoCakeLightOrange,
    cakeYellow = EchoCakeLightYellow,
    cakeTeal = EchoCakeLightTeal,
    cakePurple = EchoCakeLightPurple,
    cakeRed = EchoCakeLightRed,
    cakeMint = EchoCakeLightMint,
)

val DarkExtendedColors = ExtendedColors(
    primaryHover = EchoBrand600,
    destructiveHover = EchoRed600,
    destructiveSecondaryOutline = EchoRed200,
    disabledOutline = EchoBase900,
    disabledFill = EchoBase1000,
    successOutline = EchoBrand500Alpha40,
    success = EchoBrand500,
    onSuccess = EchoBase1000,
    secondaryFill = EchoBase900,

    textPrimary = EchoBase0,
    textTertiary = EchoBase200,
    textSecondary = EchoBase150,
    textPlaceholder = EchoBase400,
    textDisabled = EchoBase500,

    surfaceLower = EchoBase1000,
    surfaceHigher = EchoBase900,
    surfaceOutline = EchoBase100Alpha10Alt,
    overlay = EchoBase1000Alpha80,

    accentBlue = EchoBlue,
    accentPurple = EchoPurple,
    accentViolet = EchoViolet,
    accentPink = EchoPink,
    accentOrange = EchoOrange,
    accentYellow = EchoYellow,
    accentGreen = EchoGreen,
    accentTeal = EchoTeal,
    accentLightBlue = EchoLightBlue,
    accentGrey = EchoGrey,

    cakeViolet = EchoCakeDarkViolet,
    cakeGreen = EchoCakeDarkGreen,
    cakeBlue = EchoCakeDarkBlue,
    cakePink = EchoCakeDarkPink,
    cakeOrange = EchoCakeDarkOrange,
    cakeYellow = EchoCakeDarkYellow,
    cakeTeal = EchoCakeDarkTeal,
    cakePurple = EchoCakeDarkPurple,
    cakeRed = EchoCakeDarkRed,
    cakeMint = EchoCakeDarkMint,
)

val LightColorScheme = lightColorScheme(
    primary = EchoBrand500,
    onPrimary = EchoBrand1000,
    primaryContainer = EchoBrand100,
    onPrimaryContainer = EchoBrand900,

    secondary = EchoBase700,
    onSecondary = EchoBase0,
    secondaryContainer = EchoBase100,
    onSecondaryContainer = EchoBase900,

    tertiary = EchoBrand900,
    onTertiary = EchoBase0,
    tertiaryContainer = EchoBrand100,
    onTertiaryContainer = EchoBrand1000,

    error = EchoRed500,
    onError = EchoBase0,
    errorContainer = EchoRed200,
    onErrorContainer = EchoRed600,

    background = EchoBrand1000,
    onBackground = EchoBase0,
    surface = EchoBase0,
    onSurface = EchoBase1000,
    surfaceVariant = EchoBase100,
    onSurfaceVariant = EchoBase900,

    outline = EchoBase1000Alpha8,
    outlineVariant = EchoBase200,
)

val DarkColorScheme = darkColorScheme(
    primary = EchoBrand500,
    onPrimary = EchoBrand1000,
    primaryContainer = EchoBrand900,
    onPrimaryContainer = EchoBrand500,

    secondary = EchoBase400,
    onSecondary = EchoBase1000,
    secondaryContainer = EchoBase900,
    onSecondaryContainer = EchoBase150,

    tertiary = EchoBrand500,
    onTertiary = EchoBase1000,
    tertiaryContainer = EchoBrand900,
    onTertiaryContainer = EchoBrand500,

    error = EchoRed500,
    onError = EchoBase0,
    errorContainer = EchoRed600,
    onErrorContainer = EchoRed200,

    background = EchoBase1000,
    onBackground = EchoBase0,
    surface = EchoBase950,
    onSurface = EchoBase0,
    surfaceVariant = EchoBase900,
    onSurfaceVariant = EchoBase150,

    outline = EchoBase100Alpha10,
    outlineVariant = EchoBase800,
)