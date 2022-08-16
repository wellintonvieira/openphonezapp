package br.com.wellintonvieira.openphonezapp

import android.app.Application
import br.com.wellintonvieira.openphonezapp.data.di.DatasourceModule
import br.com.wellintonvieira.openphonezapp.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@App) }
        DatasourceModule.load()
        PresentationModule.load()
    }
}