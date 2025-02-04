package mx.tejate.contactskmp.presentation.contactForm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import mx.tejate.contactskmp.adapter.input.ContactController
import mx.tejate.contactskmp.core.BaseMVIViewModel
import mx.tejate.contactskmp.presentation.ContactUiMapper.toDomain
import mx.tejate.contactskmp.presentation.ContactUiMapper.toUI

class ContactFormMVI(
    private val contactController: ContactController,
) : BaseMVIViewModel<
        ContactFormUiModel.ContactFormState,
        ContactFormUiModel.ContactFormAction,
        ContactFormUiModel.ContactFormSideEffect>(
    ContactFormUiModel.ContactFormState()
) {

    override fun handleAction() {
        executeAction { action ->
            when (action) {
                is ContactFormUiModel.ContactFormAction.SaveContact -> saveContact()
                is ContactFormUiModel.ContactFormAction.LoadContact -> loadContact(action.id)
                is ContactFormUiModel.ContactFormAction.OnEmailChanged -> onEmailChanged(action.value)
                is ContactFormUiModel.ContactFormAction.OnFirstNameChanged -> onFirstNameChanged(
                    action.value
                )

                is ContactFormUiModel.ContactFormAction.OnImageChanged -> onImageChanged(action.value)
                is ContactFormUiModel.ContactFormAction.OnLastNameChanged -> onLastNameChanged(
                    action.value
                )

                is ContactFormUiModel.ContactFormAction.OnPhoneNumberChanged -> onPhoneNumberChanged(
                    action.value
                )
            }
        }
    }

    private fun saveContact() {
        if (!state.value.formInputValidation.hasError) {
            execute {
                state.value.contact?.let { contactValue ->
                    if (contactValue.id != 0L) {
                        contactController.edit(contactValue.toDomain())
                    } else {
                        contactController.saveContact(contactValue.toDomain())
                    }
                }
            }
            execute {
                updateSideEffect(ContactFormUiModel.ContactFormSideEffect.SaveContactSuccess)
            }
        }
    }

    private fun loadContact(id: Long) = execute {
        val contact = contactController.findContactById(id)?.toUI()
        updateUi { copy(contact = contact) }
    }

    private fun onFirstNameChanged(value: String) = execute(Dispatchers.IO) {
        val newState = state.value.formInputValidation.validateFirstName(value)
        updateUi { copy(formInputValidation = newState, contact = contact?.copy(name = value)) }
    }

    private fun onLastNameChanged(value: String) = execute(Dispatchers.IO) {
        val newState = state.value.formInputValidation.validateLastName(value)
        updateUi { copy(formInputValidation = newState, contact = contact?.copy(lastName = value)) }
    }

    private fun onPhoneNumberChanged(value: String) = execute(Dispatchers.IO) {
        val newState = state.value.formInputValidation.validatePhoneNumber(value)
        updateUi { copy(formInputValidation = newState, contact = contact?.copy(phone = value)) }
    }

    private fun onEmailChanged(value: String) = execute(Dispatchers.IO) {
        val newState = state.value.formInputValidation.validateEmail(value)
        updateUi { copy(formInputValidation = newState, contact = contact?.copy(email = value)) }
    }

    private fun onImageChanged(value: ByteArray) = execute(Dispatchers.IO) {
        updateUi { copy(contact = contact?.copy(photo = value)) }
    }
}