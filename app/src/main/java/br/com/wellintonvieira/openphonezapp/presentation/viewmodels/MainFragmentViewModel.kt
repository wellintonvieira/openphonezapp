package br.com.wellintonvieira.openphonezapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainFragmentViewModel: ViewModel() {

    private val _numbers = MutableLiveData<String>()
    val text: LiveData<String> = Transformations.map(_numbers) { it }

    init {
        clear()
    }

    fun add(number: Int) {
        _numbers.value += "$number"
    }

    fun remove() {
        val str = _numbers.value
        if (str != null && str.isNotEmpty()) {
            _numbers.value = str.substring(0, str.length - 1)
        }
    }

    fun clear() {
        _numbers.value = ""
    }
}