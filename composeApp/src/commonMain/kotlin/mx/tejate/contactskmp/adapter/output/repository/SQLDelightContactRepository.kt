package mx.tejate.contactskmp.adapter.output.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import mx.tejate.contactskmp.ContactDBQueries
import mx.tejate.contactskmp.adapter.output.mapper.ContactMapper.toDB
import mx.tejate.contactskmp.adapter.output.mapper.ContactMapper.toDomain
import mx.tejate.contactskmp.domain.model.Contact
import mx.tejate.contactskmp.domain.port.ContactRepository

class SQLDelightContactRepository(
    private val dbQueries: ContactDBQueries
) : ContactRepository {

    override suspend fun findById(id: Long): Contact? = withContext(Dispatchers.IO) {
        dbQueries.findById(id).executeAsOneOrNull()?.toDomain()
    }

    override suspend fun findAll(): List<Contact> = withContext(Dispatchers.IO) {
        dbQueries.selectAll().executeAsList().map { it.toDomain() }
    }

    override suspend fun save(contact: Contact): Contact = withContext(Dispatchers.IO) {
        dbQueries.insert(contact.toDB())
        return@withContext contact
    }

    override suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        dbQueries.delete(id)
    }

    override suspend fun edit(contact: Contact): Contact = withContext(Dispatchers.IO) {
        dbQueries.update(
            name = contact.name,
            lastName = contact.lastName,
            phone = contact.phone,
            email = contact.email,
            photo = contact.photo?.toByteArray(),
            id = contact.id ?: 0
        )
        return@withContext contact
    }

}