package mx.tejate.contactskmp.core.utils

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

@Composable
actual fun rememberBitmapFromBytes(bytes: ByteArray?): ImageBitmap? = remember(bytes) {
    bytes?.let {
        BitmapFactory.decodeByteArray(it, 0, it.size).asImageBitmap()
    }
}