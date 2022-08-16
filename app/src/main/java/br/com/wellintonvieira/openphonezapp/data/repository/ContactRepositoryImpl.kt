package br.com.wellintonvieira.openphonezapp.data.repository

import androidx.annotation.WorkerThread
import br.com.wellintonvieira.openphonezapp.data.models.Contact
import br.com.wellintonvieira.openphonezapp.data.source.ContactDao

class ContactRepositoryImpl(private val contactDao: ContactDao) : ContactRepository<Contact> {

    @WorkerThread
    override suspend fun insert(item: Contact) { contactDao.insert(item) }

    @WorkerThread
    override suspend fun update(item: Contact) { contactDao.update(item) }

    @WorkerThread
    override suspend fun delete(item: Contact) { contactDao.delete(item) }

    override fun load() = contactDao.loadContacts()

    override fun load(query: String) = contactDao.loadContacts(query)
}