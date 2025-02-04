package mx.tejate.contactskmp.ui.organims

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EditRow(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {

    Row(modifier) {
       FilledTonalIconButton(
           colors = IconButtonDefaults.filledTonalIconButtonColors(
               containerColor = MaterialTheme.colorScheme.secondaryContainer,
               contentColor = MaterialTheme.colorScheme.onSecondaryContainer
           ),
           onClick = {
               onEditClick.invoke()
           }
       ) {
           Icon(
               imageVector = Icons.Rounded.Edit,
               contentDescription = "Edit contact"
           )
       }
        FilledTonalIconButton(
            onClick = { onDeleteClick.invoke() },
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete contact"
            )
        }
    }

}