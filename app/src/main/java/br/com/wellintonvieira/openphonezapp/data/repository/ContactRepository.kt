package br.com.wellintonvieira.openphonezapp.data.repository

import androidx.lifecycle.LiveData

interface ContactRepository<T> {
    suspend fun insert(item: T)
    suspend fun update(item: T)
    suspend fun delete(item: T)
    fun load(): LiveData<List<T>>
    fun load(query: String): LiveData<List<T>>
}