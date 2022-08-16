package br.com.wellintonvieira.openphonezapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.wellintonvieira.openphonezapp.data.models.Contact
import br.com.wellintonvieira.openphonezapp.data.repository.ContactRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(private val contactRepositoryImpl: ContactRepositoryImpl) : ViewModel(),
    ViewModelScope<Contact> {

    lateinit var items: LiveData<List<Contact>>

    override fun insert(item: Contact) = viewModelScope.launch(Dispatchers.IO) {
        contactRepositoryImpl.insert(item)
    }

    override fun update(item: Contact) = viewModelScope.launch(Dispatchers.IO) {
        contactRepositoryImpl.update(item)
    }

    override fun delete(item: Contact) = viewModelScope.launch(Dispatchers.IO) {
        contactRepositoryImpl.delete(item)
    }

    override fun load() {
        items = contactRepositoryImpl.load()
    }

    override fun load(query: String) {
        items = contactRepositoryImpl.load("%$query%")
    }
}