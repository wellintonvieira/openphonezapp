package br.com.wellintonvieira.openphonezapp.data.repositories

import androidx.lifecycle.LiveData

interface ContactRepository<T> {
    suspend fun insert(item: T)
    suspend fun delete(item: T)
    fun load(): LiveData<List<T>>
}