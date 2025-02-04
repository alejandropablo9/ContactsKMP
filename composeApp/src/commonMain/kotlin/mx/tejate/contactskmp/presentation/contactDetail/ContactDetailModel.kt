package mx.tejate.contactskmp.presentation.contactDetail

import mx.tejate.contactskmp.core.Base
import mx.tejate.contactskmp.ui.uiModel.ContactUI

class ContactDetailModel {

    data class ContactDetailState(
        val contact: ContactUI = ContactUI(
            id = 0,
            name = "",
            lastName = "",
            phone = "",
            email = null,
        )
    ) : Base.State

    sealed class ContactDetailAction : Base.Action {
        data class LoadContact(val id: Long) : ContactDetailAction()
        data class DeleteContact(val id: Long) : ContactDetailAction()
        data class OnEditContact(val id: Long) : ContactDetailAction()
    }

    sealed class ContactDetailSideEffect : Base.SideEffect {
        object DeleteContactSuccess : ContactDetailSideEffect()
        data class OnEditContact(val id: Long) : ContactDetailSideEffect()

    }
}