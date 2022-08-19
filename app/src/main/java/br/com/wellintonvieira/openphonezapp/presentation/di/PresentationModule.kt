package br.com.wellintonvieira.openphonezapp.presentation.di

import br.com.wellintonvieira.openphonezapp.presentation.viewmodels.ContactViewModel
import br.com.wellintonvieira.openphonezapp.presentation.viewmodels.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object PresentationModule {

    fun load() { loadKoinModules(viewModelModule()) }

    private fun viewModelModule() = module {
        viewModel {
            ContactViewModel(get())
            MainFragmentViewModel()
        }
    }
}