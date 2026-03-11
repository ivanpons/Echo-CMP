package com.llimapons.core.designsystem.components.brand

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.llimapons.core.designsystem.theme.EchoTheme
import echo.core.designsystem.generated.resources.Res
import echo.core.designsystem.generated.resources.logo_chirp
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EchoBrandLogo(
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = vectorResource(Res.drawable.logo_chirp),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}

@Composable
@Preview
fun EchoBrandLogoPreview() {
    EchoTheme {
        EchoBrandLogo()
    }
}