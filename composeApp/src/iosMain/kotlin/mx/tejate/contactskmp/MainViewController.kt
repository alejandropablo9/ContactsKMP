package mx.tejate.contactskmp

import androidx.compose.ui.window.ComposeUIViewController
import mx.tejate.contactskmp.config.DatabaseDriver
import mx.tejate.contactskmp.di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }