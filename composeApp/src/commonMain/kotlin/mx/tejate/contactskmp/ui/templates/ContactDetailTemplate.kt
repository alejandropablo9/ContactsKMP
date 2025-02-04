package mx.tejate.contactskmp.ui.templates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.tejate.contactskmp.core.utils.rememberBitmapFromBytes
import mx.tejate.contactskmp.ui.molecules.ContactPhoto
import mx.tejate.contactskmp.ui.molecules.ContactTypeInfo
import mx.tejate.contactskmp.ui.molecules.EmptyContactPhoto
import mx.tejate.contactskmp.ui.organims.EditRow
import mx.tejate.contactskmp.ui.uiModel.ContactUI
import mx.tejate.contactskmp.ui.uiModel.fullName

@Composable
fun ContactDetailTemplate(
    contactUI: ContactUI,
    modifier: Modifier = Modifier,
    onEdit: () -> Unit = {},
    onDelete: () -> Unit = {},
    onBack: () -> Unit,
) {

    val sizePhoto = 150.dp
    val hasPhoto = contactUI.photo?.let {
        rememberBitmapFromBytes(it)
    }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            hasPhoto?.let {
                ContactPhoto(
                    photo = it,
                    modifier = Modifier.size(sizePhoto)
                )
            } ?: EmptyContactPhoto(
                iconSize = 50.dp,
                modifier = Modifier.size(sizePhoto)
            )

            Spacer(modifier = Modifier.size(16.dp))

            Text(
                text = contactUI.fullName(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier.fillMaxWidth(),

                )

            Spacer(modifier = Modifier.size(16.dp))

            EditRow(
                onEditClick = { onEdit.invoke() },
                onDeleteClick = { onDelete.invoke() }
            )

            Spacer(modifier = Modifier.size(16.dp))

            ContactTypeInfo(
                title = "Phone number",
                value = contactUI.phone,
                icon = Icons.Rounded.Phone,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(16.dp))

            ContactTypeInfo(
                title = "Email",
                value = contactUI.email ?: "-",
                icon = Icons.Rounded.Email,
                modifier = Modifier.fillMaxWidth()
            )
        }

        IconButton(
            onClick = {
                onBack.invoke()
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Close",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}