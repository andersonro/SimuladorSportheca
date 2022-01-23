package br.com.arodevsistemas.simuladorsportheca

import android.app.Application
import br.com.arodevsistemas.simuladorsportheca.data.di.DataModule
import br.com.arodevsistemas.simuladorsportheca.domain.di.DomainModule
import br.com.arodevsistemas.simuladorsportheca.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}