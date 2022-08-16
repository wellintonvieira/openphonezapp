package br.com.wellintonvieira.openphonezapp.data.di

import br.com.wellintonvieira.openphonezapp.data.repository.ContactRepositoryImpl
import br.com.wellintonvieira.openphonezapp.data.source.ContactDatabase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DatasourceModule {

    fun load() { loadKoinModules(databaseModules() + repositoryModules()) }

    private fun databaseModules() = module {
        single { ContactDatabase.createDatabase(get()) }
    }

    private fun repositoryModules() = module {
        factory { ContactRepositoryImpl(get()) }
    }
}