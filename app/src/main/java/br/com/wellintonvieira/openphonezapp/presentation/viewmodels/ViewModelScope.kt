package br.com.wellintonvieira.openphonezapp.presentation.viewmodels

import kotlinx.coroutines.Job

interface ViewModelScope<T> {
    fun insert(item: T): Job
    fun delete(item: T): Job
    fun load()
}