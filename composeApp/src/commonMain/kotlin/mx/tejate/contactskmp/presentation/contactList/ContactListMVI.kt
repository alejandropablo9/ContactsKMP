package mx.tejate.contactskmp.presentation.contactList

import kotlinx.coroutines.Dispatchers
import mx.tejate.contactskmp.adapter.input.ContactController
import mx.tejate.contactskmp.core.BaseMVIViewModel
import mx.tejate.contactskmp.presentation.ContactUiMapper.toUI

class ContactListMVI(
    private val contactController: ContactController
) : BaseMVIViewModel<ContactListUiModel.ContactListState, ContactListUiModel.ContactListAction, ContactListUiModel.ContactListSideEffect>(
    ContactListUiModel.ContactListState()
) {
    override fun handleAction() {
        executeAction { action ->
            when (action) {
                ContactListUiModel.ContactListAction.LoadContacts -> getAllContacts()
                is ContactListUiModel.ContactListAction.SearchContact -> {}
                ContactListUiModel.ContactListAction.HideBottomSheetAdd -> hideBottomSheetAdd()
                ContactListUiModel.ContactListAction.ShowBottomSheetAdd -> showBottomSheetAdd()
            }
        }
    }

    private fun getAllContacts() = execute {
        val contacts = contactController.getContacts()
        updateUi { copy(contacts = contacts.map { it.toUI() }) }
    }

    private fun showBottomSheetAdd() = execute(Dispatchers.Main) {
        updateUi { copy(isBottomSheetAddVisible = true) }
    }

    private fun hideBottomSheetAdd() = execute(Dispatchers.Main) {
        updateUi { copy(isBottomSheetAddVisible = false) }
    }

}