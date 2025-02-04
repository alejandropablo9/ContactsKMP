package mx.tejate.contactskmp.ui.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import mx.tejate.contactskmp.ui.atoms.TextAtom

@Composable
fun TitleAndSubtitle(title: String, subtitle: String) {
    Column {
        TextAtom(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
        )
        TextAtom(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}