package com.llimapons.core.designsystem.preview

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.llimapons.core.designsystem.components.brand.EchoBrandLogo
import com.llimapons.core.designsystem.components.layouts.EchoAdaptativeFormLayout
import com.llimapons.core.designsystem.theme.EchoTheme

@Composable
@PreviewLightDark
@PreviewScreenSizes
fun ChirpAdaptiveFormLayoutLightPreview() {
    EchoTheme {
        EchoAdaptativeFormLayout(
            headerText = "Welcome to Chirp!",
            errorText = "Login failed!",
            logo = { EchoBrandLogo() },
            formContent = {
                Text(
                    text = "Sample form title",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Sample form title 2",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        )
    }
}