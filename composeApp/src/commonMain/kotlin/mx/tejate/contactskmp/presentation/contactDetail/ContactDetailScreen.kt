package mx.tejate.contactskmp.presentation.contactDetail

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import mx.tejate.contactskmp.presentation.contactForm.ContactFormScreen
import mx.tejate.contactskmp.ui.templates.ContactDetailTemplate

class ContactDetailScreen(
    private val idContact: Long,
    private val onBack: () -> Unit
) : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<ContactDetailMVI>()
        val state by viewModel.state.collectAsState()
        val sideEffect by viewModel.sideEffect.collectAsState()
        val navigator: Navigator = LocalNavigator.currentOrThrow

        ContactDetailTemplate(
            contactUI = state.contact,
            onEdit = {
                viewModel.onAction(ContactDetailModel.ContactDetailAction.OnEditContact(idContact))
            },
            onDelete = {
                viewModel.onAction(ContactDetailModel.ContactDetailAction.DeleteContact(idContact))
            },
            onBack = { onBack.invoke() },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
        )

        LaunchedEffect(false) {
            viewModel.onAction(ContactDetailModel.ContactDetailAction.LoadContact(idContact))
        }

        sideEffect?.getContentIfNotHandled()?.let {
            when (it) {
                ContactDetailModel.ContactDetailSideEffect.DeleteContactSuccess -> navigator.pop()
                is ContactDetailModel.ContactDetailSideEffect.OnEditContact -> {
                    if (state.contact.id != 0L) {
                        navigator.push(
                            ContactFormScreen(
                                idContact = state.contact.id,
                                onBack = {
                                    navigator.pop()
                                }
                            )
                        )
                    }
                    null
                }
            }
        }

    }
}