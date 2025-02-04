package mx.tejate.contactskmp.domain.model

data class Contact(
    val id: Long?,
    val name: String,
    val lastName: String,
    val phone: String,
    val email: String?,
    val photo: List<Byte>?
)


