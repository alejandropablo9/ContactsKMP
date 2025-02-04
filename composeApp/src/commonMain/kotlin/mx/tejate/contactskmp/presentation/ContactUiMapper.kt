package mx.tejate.contactskmp.presentation

import mx.tejate.contactskmp.domain.model.Contact
import mx.tejate.contactskmp.ui.uiModel.ContactUI

object ContactUiMapper {
    fun Contact.toUI(): ContactUI = ContactUI(
        id = id ?: 0,
        name = name,
        lastName = lastName,
        phone = phone,
        email = email,
        photo = photo?.toByteArray()
    )

    fun ContactUI.toDomain(): Contact = Contact(
        id = id,
        name = name,
        lastName = lastName,
        phone = phone,
        email = email,
        photo = photo?.toList()
    )
}