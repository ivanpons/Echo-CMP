package com.llimapons.echo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.llimapons.auth.presentation.register.RegisterRoot
import com.llimapons.core.designsystem.theme.EchoTheme
import com.llimapons.echo.navigation.DeeplinkListener
import com.llimapons.echo.navigation.NavigationRoot
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val navController = rememberNavController()
    DeeplinkListener(navController)
    EchoTheme {
        NavigationRoot(navController)
    }
}