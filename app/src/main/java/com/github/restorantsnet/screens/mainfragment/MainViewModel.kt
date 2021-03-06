package com.github.restorantsnet.screens.mainfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.restorantsnet.data.Cash
import com.github.restorantsnet.utils.debugLog
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.math.BigDecimal

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val mainRepository = MainRepository(application)
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        debugLog("$throwable")
    }
    private val cashListLiveData = MutableLiveData<List<Cash>>()
    private val cashSumLiveData = MutableLiveData<BigDecimal>()

    val getCashList: LiveData<List<Cash>>
        get() = cashListLiveData
    val getCashSum: LiveData<BigDecimal>
        get() = cashSumLiveData

    fun loadCashList() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val cashList = mainRepository.geCashList()
            cashList.let {
                cashListLiveData.postValue(it)
            }
        }
    }

    fun countCash(cashList: List<Cash>) {
        viewModelScope.launch {
            val result = mainRepository.countCash(cashList)
            cashSumLiveData.postValue(result)
        }
    }
}