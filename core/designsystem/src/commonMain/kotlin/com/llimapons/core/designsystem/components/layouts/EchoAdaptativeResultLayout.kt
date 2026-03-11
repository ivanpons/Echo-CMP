package com.llimapons.core.designsystem.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.llimapons.core.designsystem.components.brand.EchoBrandLogo
import com.llimapons.core.designsystem.theme.EchoTheme
import com.llimapons.core.presentation.util.DeviceConfiguration
import com.llimapons.core.presentation.util.currentDeviceConfiguration
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EchoAdaptativeResultLayout(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val configuration = currentDeviceConfiguration()

    Scaffold(
        modifier = modifier
    ) { innerPaddings ->
        if (configuration == DeviceConfiguration.MOBILE_PORTRAIT){
            EchoSurface(
                modifier = Modifier
                    .padding(innerPaddings),
                header = {
                    Spacer(modifier = Modifier.height(32.dp))
                    EchoBrandLogo()
                    Spacer(modifier = Modifier.height(32.dp))
                }
            ){
                content()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPaddings)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                if (configuration != DeviceConfiguration.MOBILE_LANDSCAPE){
                    EchoBrandLogo()
                }
                Column(
                    modifier = Modifier
                        .widthIn(max = 480.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(32.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(horizontal = 24.dp,)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    content()
                }
            }
        }
    }

}

@Composable
@Preview
fun EchoAdaptativeResultLayoutPreview() {
    EchoTheme {
        EchoAdaptativeResultLayout(
            modifier = Modifier
                .fillMaxSize()
        ){
            Text(
                text = "Registration Successful!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}