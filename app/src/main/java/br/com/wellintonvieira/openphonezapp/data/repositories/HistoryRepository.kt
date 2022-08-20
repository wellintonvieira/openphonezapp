package br.com.wellintonvieira.openphonezapp.data.repositories

import androidx.lifecycle.LiveData

interface HistoryRepository<T> {
    suspend fun insert(item: T)
    suspend fun delete(item: T)
    fun load(): LiveData<List<T>>
    fun load(query: String): LiveData<List<T>>
}