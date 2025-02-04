package mx.tejate.contactskmp.ui.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun ContactPhoto(
    photo: ImageBitmap,
    modifier: Modifier = Modifier
) {
    Image(
        bitmap = photo,
        contentDescription = "Contact Photo",
        modifier = modifier.clip(RoundedCornerShape(35.dp)),
        contentScale = ContentScale.Crop,
    )
}
