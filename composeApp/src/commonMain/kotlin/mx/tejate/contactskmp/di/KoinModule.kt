package mx.tejate.contactskmp.di

import app.cash.sqldelight.async.coroutines.synchronous
import mx.tejate.contactskmp.AppDatabase
import mx.tejate.contactskmp.adapter.input.ContactController
import mx.tejate.contactskmp.adapter.output.repository.SQLDelightContactRepository
import mx.tejate.contactskmp.config.DatabaseDriver
import mx.tejate.contactskmp.domain.port.ContactRepository
import mx.tejate.contactskmp.domain.service.ContactService
import mx.tejate.contactskmp.presentation.contactDetail.ContactDetailMVI
import mx.tejate.contactskmp.presentation.contactForm.ContactFormMVI
import mx.tejate.contactskmp.presentation.contactList.ContactListMVI
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single<AppDatabase> {
        AppDatabase.invoke(
            DatabaseDriver().build(
                databaseName = "contacts.db",
                schema = AppDatabase.Schema.synchronous()
            )
        )
    }
    single { (get() as AppDatabase).contactDBQueries }
    single<ContactRepository> { SQLDelightContactRepository(get()) }
    single { ContactService(get()) }
    single { ContactController(get()) }
    factory { ContactListMVI(get()) }
    factory { ContactFormMVI(get()) }
    factory { ContactDetailMVI(get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(appModule)
    }
}

@Suppress("unused")
fun initKoin() = initKoin {}