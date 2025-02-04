package mx.tejate.contactskmp.ui.organims

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.tejate.contactskmp.ui.molecules.ContactCard
import mx.tejate.contactskmp.ui.uiModel.ContactUI

@Composable
fun ContactList(
    contacts: List<ContactUI>,
    modifier: Modifier,
    onContactClick: (Long) -> Unit
) {
    LazyColumn {
        items(contacts) { contact ->
            ContactCard(
                contactUI = contact,
                modifier = modifier
                    .clickable {
                        onContactClick(contact.id)
                    },
            )
        }
    }
}