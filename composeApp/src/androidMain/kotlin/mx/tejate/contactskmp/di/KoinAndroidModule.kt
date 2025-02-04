package mx.tejate.contactskmp.di

import android.content.Context
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinAndroidModule: KoinComponent {
    private val context: Context by inject()

    fun context(): Context = context
}