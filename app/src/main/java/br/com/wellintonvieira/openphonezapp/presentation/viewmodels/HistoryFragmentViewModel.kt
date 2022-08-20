package br.com.wellintonvieira.openphonezapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.wellintonvieira.openphonezapp.data.models.History
import br.com.wellintonvieira.openphonezapp.data.repositories.HistoryRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryFragmentViewModel(private val historyRepositoryImpl: HistoryRepositoryImpl) : ViewModel(),
    ViewModelScope<History> {

    var items: LiveData<List<History>> = MutableLiveData()
    var itemsFilter: LiveData<List<History>> = MutableLiveData()

    override fun insert(item: History) = viewModelScope.launch(Dispatchers.IO) {
        historyRepositoryImpl.insert(item)
    }

    override fun delete(item: History) = viewModelScope.launch(Dispatchers.IO) {
        historyRepositoryImpl.delete(item)
    }

    override fun load() {
        items = historyRepositoryImpl.load()
    }

    override fun load(query: String) {
        itemsFilter = historyRepositoryImpl.load("%$query%")
    }
}