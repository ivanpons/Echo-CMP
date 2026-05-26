package com.llimapons.core.designsystem.components.dialog

import androidx.compose.runtime.Composable
import com.llimapons.core.presentation.util.currentDeviceConfiguration

@Composable
fun EchoAdaptativeDialogSheetLayout(
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    val configuration = currentDeviceConfiguration()

    if(configuration.isMobile){
        EchoBottomSheet(
            onDismiss = onDismiss,
            content = content
        )
    } else {
        EchoDialogContent(
            onDismiss = onDismiss,
            content = content
        )
    }
}