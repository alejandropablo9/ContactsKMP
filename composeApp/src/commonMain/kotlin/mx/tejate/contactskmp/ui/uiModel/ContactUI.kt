package mx.tejate.contactskmp.ui.uiModel

data class ContactUI(
    val id: Long,
    val name: String,
    val lastName: String,
    val phone: String,
    val email: String? = null,
    val photo: ByteArray? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ContactUI

        if (id != other.id) return false
        if (name != other.name) return false
        if (lastName != other.lastName) return false
        if (phone != other.phone) return false
        if (email != other.email) return false
        if (photo != null) {
            if (other.photo == null) return false
            if (!photo.contentEquals(other.photo)) return false
        } else if (other.photo != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (photo?.contentHashCode() ?: 0)
        return result
    }

}

fun ContactUI.fullName(): String {
    return "$name $lastName"
}
