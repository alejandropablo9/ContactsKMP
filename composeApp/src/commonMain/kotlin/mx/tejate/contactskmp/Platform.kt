package mx.tejate.contactskmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform