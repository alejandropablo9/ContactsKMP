package mx.tejate.contactskmp.adapter.input

import mx.tejate.contactskmp.domain.model.Contact
import mx.tejate.contactskmp.domain.service.ContactService

class ContactController(
    private val contactService: ContactService
) {
    suspend fun getContacts(): List<Contact> = contactService.findAll()

    suspend fun findContactById(id: Long): Contact? = contactService.findById(id)

    suspend fun saveContact(contact: Contact): Contact = contactService.save(contact)

    suspend fun deleteContact(id: Long) = contactService.delete(id)

    suspend fun edit(contact: Contact) = contactService.edit(contact)
}