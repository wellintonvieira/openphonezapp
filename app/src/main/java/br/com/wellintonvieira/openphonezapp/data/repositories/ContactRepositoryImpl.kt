package br.com.wellintonvieira.openphonezapp.data.repositories

import androidx.annotation.WorkerThread
import br.com.wellintonvieira.openphonezapp.data.models.Contact
import br.com.wellintonvieira.openphonezapp.data.sources.ContactDao

class ContactRepositoryImpl(private val contactDao: ContactDao) : ContactRepository<Contact> {

    @WorkerThread
    override suspend fun insert(item: Contact) { contactDao.insert(item) }

    @WorkerThread
    override suspend fun delete(item: Contact) { contactDao.delete(item) }

    override fun load() = contactDao.load()
}