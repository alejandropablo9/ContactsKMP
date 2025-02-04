package mx.tejate.contactskmp.presentation.contactList

import mx.tejate.contactskmp.core.Base
import mx.tejate.contactskmp.ui.uiModel.ContactUI

class ContactListUiModel {
    data class ContactListState(
        val isLoading: Boolean = true,
        val isBottomSheetAddVisible: Boolean = false,
        val queryText: String = "",
        val contacts: List<ContactUI> = emptyList()
    ) : Base.State

    sealed class ContactListAction : Base.Action {
        data class SearchContact(val query: String) : ContactListAction()
        object ShowBottomSheetAdd : ContactListAction()
        object HideBottomSheetAdd : ContactListAction()
        object LoadContacts : ContactListAction()
    }

    sealed class ContactListSideEffect: Base.SideEffect {}
}