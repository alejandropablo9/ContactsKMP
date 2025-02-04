package mx.tejate.contactskmp.ui.templates

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.tejate.contactskmp.ui.atoms.TextAtom
import mx.tejate.contactskmp.ui.molecules.FloatingButton
import mx.tejate.contactskmp.ui.organims.ContactList
import mx.tejate.contactskmp.ui.uiModel.ContactUI

@Composable
fun ContactsTemplate(
    title: String,
    contacts: List<ContactUI>,
    onActionClick : () -> Unit = {},
    onContactClick : (Long) -> Unit = {},
) {
    Scaffold(
        floatingActionButton = {
            FloatingButton(
                icon = Icons.Rounded.PersonAdd,
                onClick = {
                    onActionClick()
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TextAtom(
                title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                style = MaterialTheme.typography.titleLarge,
            )
            ContactList(
                contacts = contacts,
                modifier = Modifier.fillMaxWidth(),
                onContactClick = { id ->
                  onContactClick.invoke(id)
                }
            )
        }
    }
}