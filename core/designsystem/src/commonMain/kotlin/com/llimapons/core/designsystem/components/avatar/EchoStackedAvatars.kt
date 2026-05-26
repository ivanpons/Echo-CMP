package com.llimapons.core.designsystem.components.avatar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.llimapons.core.designsystem.theme.EchoTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EchoStackedAvatars(
    avatars: List<AvatarUi>,
    modifier: Modifier = Modifier,
    size: AvatarSize = AvatarSize.SMALL,
    maxVisible: Int = 2,
    overlapPercentage: Float = 0.4f
) {
    val overlapOffset = -(size.dp * overlapPercentage)

    val visibleAvatars = avatars.take(maxVisible)
    val remainingCount = (avatars.size - maxVisible).coerceAtLeast(0)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(overlapOffset),
        verticalAlignment = Alignment.CenterVertically
    ) {
        visibleAvatars.forEach { avatarUi ->
            EchoAvatarPhoto(
                displayText = avatarUi.initials,
                size = size,
                imageUrl = avatarUi.imageUrl
            )
        }

        if (remainingCount > 0) {
            EchoAvatarPhoto(
                displayText = "$remainingCount+",
                size = size,
                textColor = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
@Preview
fun EchoStackedAvatarsPreview() {
    EchoTheme {
        EchoStackedAvatars(
            avatars = listOf(
                AvatarUi(
                    id = "",
                    username = "",
                    initials = "A",
                ),
                AvatarUi(
                    id = "",
                    username = "",
                    initials = "B",
                ),
                AvatarUi(
                    id = "",
                    username = "",
                    initials = "C",
                ),
            )
        )
    }
}