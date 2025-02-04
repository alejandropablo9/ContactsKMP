package mx.tejate.contactskmp.presentation.contactForm

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.preat.peekaboo.image.picker.ResizeOptions
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import mx.tejate.contactskmp.constants.Constants
import mx.tejate.contactskmp.ui.templates.ContactsFormTemplate
import mx.tejate.contactskmp.ui.uiModel.FormInputChanged

class ContactFormScreen(
    private val idContact: Long? = null,
    val onBack: () -> Unit
) : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<ContactFormMVI>()
        val state by viewModel.state.collectAsState()
        val sideEffect by viewModel.sideEffect.collectAsState()
        val navigator: Navigator = LocalNavigator.currentOrThrow
        val imagePicker = rememberImagePickerLauncher(
            selectionMode = SelectionMode.Single,
            scope = rememberCoroutineScope(),
            resizeOptions = ResizeOptions(
                width = Constants.WIDTH_RESIZE,
                height = Constants.HEIGHT_RESIZE,
                compressionQuality = Constants.COMPRESS_QUALITY
            ),
            onResult = { bytes ->
                if (bytes.isNotEmpty()) {
                    viewModel.onAction(
                        ContactFormUiModel.ContactFormAction.OnImageChanged(bytes.first())
                    )
                }
            }
        )

        ContactsFormTemplate(
            contactUI = state.contact,
            formInputValidation = state.formInputValidation,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            onSave = {
                viewModel.onAction(ContactFormUiModel.ContactFormAction.SaveContact)
            },
            onBack = { navigator.pop() },
            onPhoto = { imagePicker.launch() },
            onValueChange = { change ->
                when (change) {
                    is FormInputChanged.OnEmailChanged -> viewModel.onAction(
                        ContactFormUiModel.ContactFormAction.OnEmailChanged(change.value)
                    )

                    is FormInputChanged.OnFirstNameChanged -> viewModel.onAction(
                        ContactFormUiModel.ContactFormAction.OnFirstNameChanged(change.value)
                    )

                    is FormInputChanged.OnImageChanged -> viewModel.onAction(
                        ContactFormUiModel.ContactFormAction.OnImageChanged(change.value)
                    )

                    is FormInputChanged.OnLastNameChanged -> viewModel.onAction(
                        ContactFormUiModel.ContactFormAction.OnLastNameChanged(change.value)
                    )

                    is FormInputChanged.OnPhoneNumberChanged -> viewModel.onAction(
                        ContactFormUiModel.ContactFormAction.OnPhoneNumberChanged(change.value)
                    )
                }
            }
        )

        sideEffect?.getContentIfNotHandled()?.let {
            when (it) {
                ContactFormUiModel.ContactFormSideEffect.SaveContactSuccess -> navigator.pop()
            }
        }

        LaunchedEffect(false) {
            idContact?.let {
                viewModel.onAction(ContactFormUiModel.ContactFormAction.LoadContact(idContact))
            }
        }
    }
}