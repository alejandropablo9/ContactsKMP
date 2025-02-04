package mx.tejate.contactskmp.presentation.contactForm

import mx.tejate.contactskmp.core.Base
import mx.tejate.contactskmp.ui.uiModel.ContactUI
import mx.tejate.contactskmp.ui.uiModel.FormInputValidation

class ContactFormUiModel {

    data class ContactFormState(
        val contact: ContactUI? = ContactUI(
            id = 0,
            name = "",
            lastName = "",
            phone = "",
            email = "",
        ),
        val formInputValidation: FormInputValidation = FormInputValidation()
    ) : Base.State

    sealed class ContactFormAction : Base.Action {
        object SaveContact : ContactFormAction()
        data class LoadContact(val id: Long) : ContactFormAction()
        data class OnFirstNameChanged(val value: String) : ContactFormAction()
        data class OnLastNameChanged(val value: String) : ContactFormAction()
        data class OnPhoneNumberChanged(val value: String) : ContactFormAction()
        data class OnEmailChanged(val value: String) : ContactFormAction()
        data class OnImageChanged(val value: ByteArray) : ContactFormAction() {
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || this::class != other::class) return false

                other as OnImageChanged

                return value.contentEquals(other.value)
            }

            override fun hashCode(): Int {
                return value.contentHashCode()
            }
        }
    }

    sealed class ContactFormSideEffect: Base.SideEffect {
        object SaveContactSuccess : ContactFormSideEffect()
    }
}