package br.com.wellintonvieira.openphonezapp.data.di

import br.com.wellintonvieira.openphonezapp.data.repositories.HistoryRepositoryImpl
import br.com.wellintonvieira.openphonezapp.data.sources.AppDatabase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DatasourceModule {

    fun load() { loadKoinModules(databaseModules() + repositoryModules()) }

    private fun databaseModules() = module {
        single { AppDatabase.createDatabase(get()) }
    }

    private fun repositoryModules() = module {
        factory { HistoryRepositoryImpl(get()) }
    }
}