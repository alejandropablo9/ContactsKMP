package mx.tejate.contactskmp.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeImageBitmap
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image

@Composable
actual fun rememberBitmapFromBytes(bytes: ByteArray?): ImageBitmap? = remember(bytes) {
    bytes?.let {
        Bitmap.makeFromImage(
            Image.makeFromEncoded(it)
        ).asComposeImageBitmap()
    }
}