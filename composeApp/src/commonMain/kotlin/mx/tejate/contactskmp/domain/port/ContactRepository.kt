package mx.tejate.contactskmp.domain.port

import mx.tejate.contactskmp.domain.model.Contact

interface ContactRepository {
    /**
     * Método para encontrar un contacto por su ID en la base de datos.
     */
    suspend fun findById(id: Long): Contact?

    /**
     * Método para encontrar todos los contactos en la base de datos.
     */
    suspend fun findAll(): List<Contact>

    /**
     * Método para guardar un contacto en la base de datos.
     */
    suspend fun save(contact: Contact): Contact

    /**
     * Método para eliminar un contacto por su ID de la base de datos.
     */
    suspend fun delete(id: Long)

    /**
     * Método para editar un contacto en la base de datos.
     */
    suspend fun edit(contact: Contact): Contact
}