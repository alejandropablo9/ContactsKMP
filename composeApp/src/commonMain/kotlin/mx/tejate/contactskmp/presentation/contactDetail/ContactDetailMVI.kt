package mx.tejate.contactskmp.presentation.contactDetail

import mx.tejate.contactskmp.adapter.input.ContactController
import mx.tejate.contactskmp.core.BaseMVIViewModel
import mx.tejate.contactskmp.presentation.ContactUiMapper.toUI

class ContactDetailMVI(
    private val contactController: ContactController,
) : BaseMVIViewModel<
        ContactDetailModel.ContactDetailState,
        ContactDetailModel.ContactDetailAction,
        ContactDetailModel.ContactDetailSideEffect>(
    ContactDetailModel.ContactDetailState()
) {

    override fun handleAction() {
        executeAction { action ->
            when (action) {
                is ContactDetailModel.ContactDetailAction.DeleteContact -> deleteContact()
                is ContactDetailModel.ContactDetailAction.LoadContact -> loadContact(action.id)
                is ContactDetailModel.ContactDetailAction.OnEditContact -> editContact(action.id)
            }
        }
    }

    private fun deleteContact() {
        execute {
            contactController.deleteContact(state.value.contact.id)
        }
        execute {
            updateSideEffect(ContactDetailModel.ContactDetailSideEffect.DeleteContactSuccess)
        }
    }

    private fun editContact(id: Long) = execute {
        updateSideEffect(ContactDetailModel.ContactDetailSideEffect.OnEditContact(id))
    }

    private fun loadContact(id: Long) = execute {
        val contact = contactController.findContactById(id)?.toUI()
        contact?.let {
            updateUi { copy(contact = contact) }
        }
    }

}