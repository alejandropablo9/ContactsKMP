package mx.tejate.contactskmp.ui.molecules

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun FloatingButton(
    icon: ImageVector,
    onClick: () -> Unit,
) {
    FloatingActionButton(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Icon Description"
        )
    }
}