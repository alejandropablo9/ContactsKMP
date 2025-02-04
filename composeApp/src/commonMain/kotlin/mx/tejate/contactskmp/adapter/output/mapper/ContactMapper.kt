package mx.tejate.contactskmp.adapter.output.mapper

import mx.tejate.contactskmp.domain.model.Contact
import mx.tejate.contactskmp.Contact as ContactDB

object ContactMapper {

    fun ContactDB.toDomain(): Contact = Contact(
        id = id,
        name = name,
        lastName = lastName,
        phone = phone,
        email = email,
        photo = photo?.toList()
    )

    fun Contact.toDB(id: Long = 0): ContactDB = ContactDB(
        id = id,
        name = name,
        lastName = lastName,
        phone = phone,
        email = email,
        photo = photo?.toByteArray()
    )

}