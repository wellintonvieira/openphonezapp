package br.com.wellintonvieira.openphonezapp.data.repositories

import androidx.annotation.WorkerThread
import br.com.wellintonvieira.openphonezapp.data.models.History
import br.com.wellintonvieira.openphonezapp.data.sources.HistoryDao

class HistoryRepositoryImpl(private val historyDao: HistoryDao) : HistoryRepository<History> {

    @WorkerThread
    override suspend fun insert(item: History) { historyDao.insert(item) }

    @WorkerThread
    override suspend fun delete(item: History) { historyDao.delete(item) }

    override fun load() = historyDao.load()

    override fun load(query: String) = historyDao.load(query)
}