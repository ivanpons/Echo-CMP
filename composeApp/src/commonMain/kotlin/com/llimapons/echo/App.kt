package com.llimapons.echo

import androidx.compose.runtime.Composable
import com.llimapons.auth.presentation.register.RegisterRoot
import com.llimapons.core.designsystem.theme.EchoTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    EchoTheme {
        RegisterRoot(
            onRegisterSuccess = {}
        )
    }
}