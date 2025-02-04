package mx.tejate.contactskmp.presentation.contactList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import mx.tejate.contactskmp.presentation.contactDetail.ContactDetailScreen
import mx.tejate.contactskmp.presentation.contactForm.ContactFormScreen
import mx.tejate.contactskmp.ui.templates.ContactsTemplate

class ContactListScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<ContactListMVI>()
        val state by viewModel.state.collectAsState()
        val navigator: Navigator = LocalNavigator.currentOrThrow

        ContactsTemplate(
            title = "Contacts",
            contacts = state.contacts,
            onActionClick = {
                navigator.push(
                    ContactFormScreen(
                        onBack = {
                            navigator.pop()
                        }
                    )
                )
            },
            onContactClick = { id ->
                navigator.push(
                    ContactDetailScreen(
                        idContact = id,
                        onBack = {
                            navigator.pop()
                        }
                    )
                )
            }
        )
        LaunchedEffect(false) {
            viewModel.onAction(ContactListUiModel.ContactListAction.LoadContacts)
        }

    }
}