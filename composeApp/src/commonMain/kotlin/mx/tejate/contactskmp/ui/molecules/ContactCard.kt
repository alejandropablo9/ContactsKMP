package mx.tejate.contactskmp.ui.molecules

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.tejate.contactskmp.core.utils.rememberBitmapFromBytes
import mx.tejate.contactskmp.ui.uiModel.ContactUI
import mx.tejate.contactskmp.ui.uiModel.fullName

@Composable
fun ContactCard(
    contactUI: ContactUI,
    modifier: Modifier
) {

    val sizePhoto = 50.dp
    val hasPhoto = contactUI.photo?.let {
        rememberBitmapFromBytes(it)
    }

    Row(
        modifier = modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        hasPhoto?.let {
            ContactPhoto(
                photo = it,
                modifier = Modifier.size(sizePhoto)
            )
        } ?: EmptyContactPhoto(
            modifier = Modifier.size(sizePhoto)
        )

        Spacer(Modifier.size(16.dp))

        TitleAndSubtitle(
            title = contactUI.fullName(),
            subtitle = contactUI.phone
        )
    }

}