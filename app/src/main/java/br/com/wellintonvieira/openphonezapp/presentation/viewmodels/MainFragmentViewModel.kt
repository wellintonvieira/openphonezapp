package br.com.wellintonvieira.openphonezapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainFragmentViewModel: ViewModel() {

    private val numbers = MutableLiveData<MutableList<Int>>()

    fun add(number: Int) {
        numbers.value?.add(number)
    }

    fun remove() {
        numbers.value?.removeLastOrNull()
    }

    fun getNumbers() = numbers
}