package com.github.restorantsnet.screens.mainfragment

import android.content.Context
import com.github.restorantsnet.data.Cash
import com.github.restorantsnet.screens.adapters.MoshiCashAdapter
import com.github.restorantsnet.utils.debugLog
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val context: Context) {
    private val cashListType = Types.newParameterizedType(List::class.java, Cash::class.java)
    private val moshi = Moshi.Builder()
        .add(MoshiCashAdapter())
        .build()

    private val moshiAdapter = moshi.adapter<List<Cash>>(cashListType).nonNull()

    suspend fun geCashList(): List<Cash> {
        return withContext(Dispatchers.IO) {
            val string = context.resources.assets.open("cashValues.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
            moshiAdapter.fromJson(string) ?: emptyList()
        }
    }

    suspend fun countCash(cashList: List<Cash>): java.math.BigDecimal {
        return withContext(Dispatchers.Default) {
            cashList.map {
                it.countMethod.countCash(
                    cashCount = it.cashAmount,
                    cashValue =
                    it.value.toDoubleOrNull() ?: 0.toDouble()
                )
            }.sumList()
        }
    }

    private fun List<Double>.sumList(): java.math.BigDecimal {
        var tempSum = 0.toDouble()
        this.forEach {
            tempSum += it
        }
        (tempSum*100).toInt()/100
        debugLog(tempSum.toString())
        return tempSum.toBigDecimal()
    }
}