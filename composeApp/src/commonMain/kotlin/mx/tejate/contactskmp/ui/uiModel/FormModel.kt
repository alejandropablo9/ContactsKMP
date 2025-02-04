package mx.tejate.contactskmp.ui.uiModel

import mx.tejate.contactskmp.constants.Constants.EMAIL_REGEX
import mx.tejate.contactskmp.constants.Constants.MAX_CHARACTERS
import mx.tejate.contactskmp.constants.Constants.MIN_CHARACTERS
import mx.tejate.contactskmp.constants.Constants.PHONE_NUMBER_LENGTH

data class FormInputValidation(
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val phoneNumberError: String? = null,
    val emailError: String? = null,
) {

    val hasError: Boolean = listOfNotNull(
        firstNameError,
        lastNameError,
        phoneNumberError,
        emailError,
    ).isNotEmpty()

    fun validateFirstName(value: String?) = copy(firstNameError = validateName(value, "First name"))
    fun validateLastName(value: String?) = copy(lastNameError = validateName(value, "Last name"))
    fun validatePhoneNumber(value: String?) = copy(phoneNumberError = validatePhone(value))
    fun validateEmail(value: String?) = copy(emailError = validateOptionalEmail(value))

    private fun validateName(value: String?, fieldName: String): String? {
        return when {
            value.isNullOrBlank() -> "$fieldName is required"
            value.length < MIN_CHARACTERS -> "$fieldName must be at least 3 characters"
            value.length > MAX_CHARACTERS -> "$fieldName must be less than 50 characters"
            else -> null
        }
    }

    private fun validateTextEmail(value: String): String? {
        return when {
            !value.matches(EMAIL_REGEX.toRegex()) -> "Invalid email format"
            else -> null
        }
    }

    private fun validateOptionalEmail(value: String?): String? {
        return if (value.isNullOrBlank()) null
        else validateTextEmail(value)
    }


    private fun validatePhone(value: String?): String? {
        return when {
            value.isNullOrBlank() -> "Phone number is required"
            value.length != PHONE_NUMBER_LENGTH -> "Phone number must be 10 digits"
            !value.all { it.isDigit() } -> "Phone number must be digits"
            else -> null
        }
    }
}

sealed class FormInputChanged {
    data class OnFirstNameChanged(val value: String) : FormInputChanged()
    data class OnLastNameChanged(val value: String) : FormInputChanged()
    data class OnPhoneNumberChanged(val value: String) : FormInputChanged()
    data class OnEmailChanged(val value: String) : FormInputChanged()
    data class OnImageChanged(val value: ByteArray) : FormInputChanged() {
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