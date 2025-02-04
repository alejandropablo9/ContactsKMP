package mx.tejate.contactskmp

import android.app.Application
import mx.tejate.contactskmp.config.DatabaseDriver
import mx.tejate.contactskmp.di.appModule
import mx.tejate.contactskmp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Contactskmp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@Contactskmp)
            androidLogger( )
        }
    }

}