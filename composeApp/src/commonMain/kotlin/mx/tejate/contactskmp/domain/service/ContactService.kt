package mx.tejate.contactskmp.domain.service

import mx.tejate.contactskmp.domain.model.Contact
import mx.tejate.contactskmp.domain.port.ContactRepository

class ContactService(
    private val contactRepository: ContactRepository
) {

    suspend fun findAll(): List<Contact> = contactRepository.findAll()

    suspend fun save(contact: Contact) = contactRepository.save(contact)

    suspend fun delete(id: Long) = contactRepository.delete(id)

    suspend fun findById(id: Long): Contact? = contactRepository.findById(id)

    suspend fun edit(contact: Contact) = contactRepository.edit(contact)
}